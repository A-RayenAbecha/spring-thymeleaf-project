# Use Dev Container image with Java 21
FROM mcr.microsoft.com/devcontainers/java:21

# Set non-interactive frontend to avoid prompts
ENV DEBIAN_FRONTEND=noninteractive
ENV TZ=Etc/UTC

# Install MySQL, Apache2, PHP, phpMyAdmin
RUN apt-get update && \
    apt-get install -y tzdata && \
    apt-get install -y \
    mysql-server \
    apache2 \
    php \
    php-mysql \
    phpmyadmin && \
    ln -s /usr/share/phpmyadmin /var/www/html/phpmyadmin

# Configure MySQL
RUN service mysql start && \
    mysql -e "CREATE USER IF NOT EXISTS 'root'@'localhost';" && \
    mysql -e "GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;" && \
    mysql -e "FLUSH PRIVILEGES;" && \
    mysql -e "CREATE DATABASE IF NOT EXISTS Aziz;"

# Expose needed ports
EXPOSE 3306   # MySQL
EXPOSE 8089   # Spring Boot
EXPOSE 8081   # phpMyAdmin (via Apache)

# Start services
CMD service mysql start && \
    service apache2 start && \
    tail -f /dev/null
