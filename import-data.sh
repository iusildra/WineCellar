##################################
### Import data from SQL files ###
##################################

SQL_FILES_LOC="./sql"
SQL_FILES=$(ls $SQL_FILES_LOC)

for FILE in $SQL_FILES
do
    docker exec mychiefcook-db psql -U postgres -f home/$FILE
done
