# TP - TACS - GRUPO 1

## Ejecución de spring con Docker

1) Para buildear la imagen:
```bash
docker build -t <tag_name>:<version> .
```

2) Ejecutar el contenedor
```bash
docker run --rm --name <container_name> <tag_name>:<version>
```

Nota: Si no se especifica ninguna version, por default es latest
