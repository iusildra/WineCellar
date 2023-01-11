# MyChiefCook

This project is part of the course "Software Engineering Practices" at Polytech Montpellier, in fourth year of Computer Science and Management.

## Authors

Project directed by Alexis FONDARD MARTIN, Corentin CLEMENT, Anaïs VELCKER, Richard MARTIN & Lucas NOUGUIER.

Status: In development...

## Setup

You need to have installed [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/). To launch the database run the following command from the project's root (otherwise add `-f <path/to/docker-compose.yml>`):

```zsh
  docker-compose up
```

## How to run

```zsh
mvn clean javafx:run
```

## SQL Migrations

Keep a track of each SQL migration (create a new file) to automatically load tables on each run whithout having to do it manually. You must name that properly with a version number to ensure the order of execution

To modify the database schema, do not change it inside the shell, but in the files in the `sql` folder. Then delete the volume with and relaunch the `docker-compose` command. To find the volume name, run `docker volume ls` (it should be something like `cookingchef_chief-db-vol`).

```zsh
  docker-compose down             # Stop the containers
  docker volume rm <volume_name>  # Delete the volume
  docker-compose up               # Relaunch the containers
```

If your volume's name is the same as excepted, you can use the script `./relaunchdb.sh` to do it automatically.
