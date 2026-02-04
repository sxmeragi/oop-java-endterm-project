package com.aitu.oop.controller;

import java.util.Scanner;

import com.aitu.oop.entity.User;
import com.aitu.oop.service.UserService;

public class UserController {

    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    private final MovieController movieController;
    private final ReviewController reviewController;

    public UserController(UserService userService,
            MovieController movieController,
            ReviewController reviewController) {
        this.userService = userService;
        this.movieController = movieController;
        this.reviewController = reviewController;

    }

    public void Start() {
        User currentUser = Login();

        if (currentUser == null) {
            System.out.println("Login failed");
            return;
        }
        if (currentUser.getRole().equalsIgnoreCase("ADMIN")) {

        } else {

        }

    }

    public User Login() {

        System.out.print("Enter Login: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.login(username, password);

        System.out.println("Succes login");
        System.out.println("Welcome" + user.getUserName());

        return user;
    }

    private void adminMenu(User user) {
        while (true) {
            System.out.println("""
                    1. Show movies
                    2. Add movie
                    3. Delete movie
                    4. Exit
                    """);
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    movieController.showMovies();
                case 2:
                    movieController.addMovie();
                case 3:
                    movieController.deleteMovie();
                case 4:
                    return;
                default:
                    System.out.println("Invalid option");

            }
        }

    }

    private void userMenu(User user) {
        while (true) {
            System.out.println("""
                    1. Show movies
                    2. Add review
                    3. Exit
                    """);

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    movieController.showMovies();
                case 2:
                    reviewController.addReview(user);
                case 3:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }

    }
}