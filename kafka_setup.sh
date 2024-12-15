# Set variables
ZOOKEEPER_CONTAINER_NAME="zookeeper"
KAFKA_CONTAINER_NAME="kafka"
NETWORK_NAME="kafka-network"
ZOOKEEPER_IMAGE="confluentinc/cp-zookeeper"
KAFKA_IMAGE="confluentinc/cp-kafka"
ZOOKEEPER_PORT=2181
KAFKA_PORT=9092

# Create a Docker network if it doesn't exist
echo "Creating Docker network..."
docker network inspect $NETWORK_NAME >/dev/null 2>&1 || docker network create $NETWORK_NAME

# Run Zookeeper container
echo "Starting Zookeeper container..."
docker run -d \
  --name $ZOOKEEPER_CONTAINER_NAME \
  --network $NETWORK_NAME \
  -e ZOOKEEPER_CLIENT_PORT=$ZOOKEEPER_PORT \
  -e ZOOKEEPER_TICK_TIME=2000 \
  $ZOOKEEPER_IMAGE

# Run Kafka container
echo "Starting Kafka container..."
docker run -d \
  --name $KAFKA_CONTAINER_NAME \
  --network $NETWORK_NAME \
  -e KAFKA_BROKER_ID=1 \
  -e KAFKA_ZOOKEEPER_CONNECT=$ZOOKEEPER_CONTAINER_NAME:$ZOOKEEPER_PORT \
  -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:$KAFKA_PORT \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:$KAFKA_PORT \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  -p $KAFKA_PORT:$KAFKA_PORT \
  $KAFKA_IMAGE

# Check running containers
echo "Checking running containers..."
docker ps

echo "Kafka and Zookeeper are running!"
