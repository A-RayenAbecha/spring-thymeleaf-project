# Base image with Java and Maven
FROM mcr.microsoft.com/devcontainers/java:17

# Install MySQL Server, Apache2, PHP, phpMyAdmin
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y \
    mysql-server \
    apache2 \
    php \
    php-mysql \
    phpmyadmin

# Setup phpMyAdmin symlink for Apache
RUN ln -s /usr/share/phpmyadmin /var/www/html/phpmyadmin

# Create MySQL user and database
RUN service mysql start && \
    mysql -e "CREATE USER IF NOT EXISTS 'root'@'localhost';" && \
    mysql -e "GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;" && \
    mysql -e "FLUSH PRIVILEGES;" && \
    mysql -e "CREATE DATABASE IF NOT EXISTS Aziz;"

# Expose necessary ports
EXPOSE 3306   # MySQL
EXPOSE 8089   # Spring Boot
EXPOSE 8081   # phpMyAdmin (via Apache)

# Start services when container starts
CMD service mysql start && \
    service apache2 start && \
    tail -f /dev/null
