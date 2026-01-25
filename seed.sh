#!/bin/bash

# Video Library Database Seeding Script
# Generates large dataset for testing and performance evaluation

set -e

# Configuration
DB_HOST="${DB_HOST:-localhost}"
DB_PORT="${DB_PORT:-3306}"
DB_NAME="${DB_NAME:-palaash}"
DB_USER="${DB_USER:-videolibrary}"
DB_PASS="${DB_PASS:-videolibrary123}"

# Number of records to generate
NUM_VIDEOS="${NUM_VIDEOS:-100000}"
NUM_CUSTOMERS="${NUM_CUSTOMERS:-100000}"
NUM_TRANSACTIONS="${NUM_TRANSACTIONS:-800000}"

echo "================================================"
echo "Video Library Database Seeding Script"
echo "================================================"
echo "Host: $DB_HOST:$DB_PORT"
echo "Database: $DB_NAME"
echo "Videos: $NUM_VIDEOS"
echo "Customers: $NUM_CUSTOMERS"
echo "Transactions: $NUM_TRANSACTIONS"
echo "================================================"

# Wait for MySQL to be ready
echo "Waiting for MySQL to be ready..."
for i in {1..30}; do
    if mysql -h"$DB_HOST" -P"$DB_PORT" -u"$DB_USER" -p"$DB_PASS" -e "SELECT 1" "$DB_NAME" &> /dev/null; then
        echo "MySQL is ready!"
        break
    fi
    echo "Waiting... ($i/30)"
    sleep 2
done

# Arrays for random data generation
GENRES=("Action" "Comedy" "Drama" "Horror" "Sci-Fi" "Romance" "Thriller" "Animation" "Documentary" "Fantasy" "Mystery" "Adventure" "Crime" "Family" "Musical" "War" "Western" "Biography")
RATINGS=("U" "U/A" "A" "R" "PG" "PG-13")
FIRST_NAMES=("John" "Jane" "Mike" "Sarah" "David" "Emma" "Chris" "Lisa" "Tom" "Mary" "James" "Patricia" "Robert" "Jennifer" "Michael" "Linda" "William" "Elizabeth" "Richard" "Susan")
LAST_NAMES=("Smith" "Johnson" "Williams" "Brown" "Jones" "Garcia" "Miller" "Davis" "Rodriguez" "Martinez" "Hernandez" "Lopez" "Gonzalez" "Wilson" "Anderson" "Thomas" "Taylor" "Moore" "Jackson" "Martin")
CITIES=("Mumbai" "Delhi" "Bangalore" "Hyderabad" "Chennai" "Kolkata" "Pune" "Ahmedabad" "Jaipur" "Lucknow")

echo ""
echo "Step 1/3: Generating $NUM_VIDEOS videos..."

# Generate videos in batches
BATCH_SIZE=5000
for ((i=1; i<=NUM_VIDEOS; i+=BATCH_SIZE)); do
    END=$((i + BATCH_SIZE - 1))
    if [ $END -gt $NUM_VIDEOS ]; then
        END=$NUM_VIDEOS
    fi
    
    echo "  Inserting videos $i to $END..."
    
    SQL="INSERT INTO vcd_record (vcd_id, vcd_title, vcd_genre, vcd_rating, vcd_price_in_rupees, NO_OF_UNITS_AVAILABLE) VALUES "
    
    for ((j=i; j<=END; j++)); do
        VCD_ID=$(printf "%04d" $j)
        GENRE=${GENRES[$RANDOM % ${#GENRES[@]}]}
        RATING=${RATINGS[$RANDOM % ${#RATINGS[@]}]}
        PRICE=$((50 + RANDOM % 450))
        UNITS=$((1 + RANDOM % 20))
        TITLE="Movie Title $j"
        
        SQL+="('$VCD_ID', '$TITLE', '$GENRE', '$RATING', $PRICE, $UNITS)"
        
        if [ $j -lt $END ]; then
            SQL+=","
        fi
    done
    
    SQL+=";"
    
    mysql -h"$DB_HOST" -P"$DB_PORT" -u"$DB_USER" -p"$DB_PASS" "$DB_NAME" -e "$SQL" 2>/dev/null
done

echo ""
echo "Step 2/3: Generating $NUM_CUSTOMERS customers..."

# Generate customers in batches
for ((i=1; i<=NUM_CUSTOMERS; i+=BATCH_SIZE)); do
    END=$((i + BATCH_SIZE - 1))
    if [ $END -gt $NUM_CUSTOMERS ]; then
        END=$NUM_CUSTOMERS
    fi
    
    echo "  Inserting customers $i to $END..."
    
    SQL="INSERT INTO customer_record (cust_id, cust_name, cust_ph_no, cust_address) VALUES "
    
    for ((j=i; j<=END; j++)); do
        CUST_ID=$(printf "%04d" $j)
        FIRST=${FIRST_NAMES[$RANDOM % ${#FIRST_NAMES[@]}]}
        LAST=${LAST_NAMES[$RANDOM % ${#LAST_NAMES[@]}]}
        NAME="$FIRST $LAST"
        PHONE=$(printf "%010d" $((1000000000 + RANDOM % 9000000000)))
        CITY=${CITIES[$RANDOM % ${#CITIES[@]}]}
        ADDRESS="$((1 + RANDOM % 999)) Street, $CITY"
        
        SQL+="('$CUST_ID', '$NAME', '$PHONE', '$ADDRESS')"
        
        if [ $j -lt $END ]; then
            SQL+=","
        fi
    done
    
    SQL+=";"
    
    mysql -h"$DB_HOST" -P"$DB_PORT" -u"$DB_USER" -p"$DB_PASS" "$DB_NAME" -e "$SQL" 2>/dev/null
done

echo ""
echo "Step 3/3: Generating $NUM_TRANSACTIONS transactions..."

# Generate transactions in batches
for ((i=1; i<=NUM_TRANSACTIONS; i+=BATCH_SIZE)); do
    END=$((i + BATCH_SIZE - 1))
    if [ $END -gt $NUM_TRANSACTIONS ]; then
        END=$NUM_TRANSACTIONS
    fi
    
    echo "  Inserting transactions $i to $END..."
    
    SQL="INSERT INTO transactionrcvd (order_id, vcd_id, vcd_title, cust_id, cust_name, date_of_issue, date_of_return, date_returned_on) VALUES "
    
    for ((j=i; j<=END; j++)); do
        ORDER_ID=$j
        VCD_NUM=$((1 + RANDOM % NUM_VIDEOS))
        CUST_NUM=$((1 + RANDOM % NUM_CUSTOMERS))
        VCD_ID=$(printf "%04d" $VCD_NUM)
        CUST_ID=$(printf "%04d" $CUST_NUM)
        
        # Random date in the last 365 days
        DAYS_AGO=$((RANDOM % 365))
        ISSUE_DATE=$(date -d "$DAYS_AGO days ago" +%Y/%m/%d 2>/dev/null || date -v -${DAYS_AGO}d +%Y/%m/%d)
        
        # Return date is 7-14 days after issue
        RENTAL_DAYS=$((7 + RANDOM % 7))
        RETURN_DATE=$(date -d "$ISSUE_DATE +$RENTAL_DAYS days" +%Y/%m/%d 2>/dev/null || date -j -f %Y/%m/%d -v +${RENTAL_DAYS}d "$ISSUE_DATE" +%Y/%m/%d)
        
        # 70% returned, 30% still pending
        if [ $((RANDOM % 10)) -lt 7 ]; then
            RETURNED_DATE="'$RETURN_DATE'"
        else
            RETURNED_DATE="NULL"
        fi
        
        TITLE="Movie Title $VCD_NUM"
        NAME="Customer $CUST_NUM"
        
        SQL+="($ORDER_ID, '$VCD_ID', '$TITLE', '$CUST_ID', '$NAME', '$ISSUE_DATE', '$RETURN_DATE', $RETURNED_DATE)"
        
        if [ $j -lt $END ]; then
            SQL+=","
        fi
    done
    
    SQL+=";"
    
    mysql -h"$DB_HOST" -P"$DB_PORT" -u"$DB_USER" -p"$DB_PASS" "$DB_NAME" -e "$SQL" 2>/dev/null
done

echo ""
echo "================================================"
echo "Seeding Complete!"
echo "================================================"
echo "Videos created: $NUM_VIDEOS"
echo "Customers created: $NUM_CUSTOMERS"
echo "Transactions created: $NUM_TRANSACTIONS"
echo ""
echo "Verifying database..."
mysql -h"$DB_HOST" -P"$DB_PORT" -u"$DB_USER" -p"$DB_PASS" "$DB_NAME" -e "
    SELECT 'Videos' as Table_Name, COUNT(*) as Count FROM vcd_record
    UNION ALL
    SELECT 'Customers', COUNT(*) FROM customer_record
    UNION ALL
    SELECT 'Transactions', COUNT(*) FROM transactionrcvd
    UNION ALL
    SELECT 'Pending Returns', COUNT(*) FROM transactionrcvd WHERE date_returned_on IS NULL;
"
echo "================================================"
