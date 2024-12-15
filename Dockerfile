FROM ubuntu:latest
LABEL authors="alkh"

ENTRYPOINT ["top", "-b"]