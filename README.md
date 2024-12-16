# как поднять

1) создать .env
```sh
PGADMIN_DEFAULT_EMAIL=мыло_от_пгадмин
PGADMIN_DEFAULT_PASSWORD=пароль_от_пгадмин
POSTGRES_PASSWORD=пароль_от_бд
```

2) `docker compose --env-file=.env up -d`
