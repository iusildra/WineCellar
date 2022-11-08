#############################################################
### Small script to automatize postgresql database launch ###
#############################################################

# Stop the script on exception throw
set -e

CONTAINER_NAME="mychiefcook-db"
VOLUME_NAME="mychiefcook-data"
HOST=127.0.0.1
HOST_PORT=5433
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

# Check for existing docker volume
if [[ $(docker volume ls | grep $VOLUME_NAME | wc -l) == 0 ]] ; then
    echo -e "[\e[1;37m INFO \e[0m] No existing volume for postgres container, creating one..."
    docker volume create --label=mychiefcook-data
    echo -e "[\e[1;32m SUCCESS \e[0m] Volume created" 
else
    echo -e "[\e[1;37m INFO \e[0m] Volume found"

fi

# Launch postgresql database
echo -e "[\e[1;37m INFO \e[0m] Launching postgresql container"
    docker run --name $CONTAINER_NAME --rm -P -p $HOST:$HOST_PORT:5432 -e POSTGRES_USER=$POSTGRES_USER -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD -v $VOLUME_NAME:/var/lib/postgresql/data -d postgres
echo -e "[\e[1;32m SUCCESS \e[0m] Container $CONTAINER_NAME launched on $HOST:$HOST_PORT"

# Command to connect to the database
echo -e "\e[1;37mTo connect to the database use : docker exec -it $CONTAINER_NAME psql -U $POSTGRES_USER\e[0m "
