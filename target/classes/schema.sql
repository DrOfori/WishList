DROP TABLE IF EXISTS wish;
DROP TABLE IF EXISTS wishlist;

CREATE TABLE wishlist (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE wish (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      price DECIMAL(10,2),
                      link VARCHAR(2048),
                      wishlist_id INT NOT NULL,
                      FOREIGN KEY (wishlist_id) REFERENCES wishlist(id) ON DELETE CASCADE
);