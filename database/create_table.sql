CREATE TABLE genres (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    release_year INT,
    rating DECIMAL(2,1),
    genre_id INT REFERENCES genres(id)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(150) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('USER', 'ADMIN'))
);

CREATE TABLE reviews (
    id SERIAL PRIMARY KEY,
    movie_id INT NOT NULL REFERENCES movies(id),
    user_id INT NOT NULL REFERENCES users(id),
    rating DECIMAL(2,1) NOT NULL CHECK (rating >= 0 AND rating <= 10),
    comment_text TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
