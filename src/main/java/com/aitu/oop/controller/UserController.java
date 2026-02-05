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
    while (true) {
        System.out.println("\n=== Movie-Search ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");

        int choice = Integer.parseInt(scanner.nextLine());

        User currentUser = null;

        switch (choice) {
            case 1 -> currentUser = login();
            case 2 -> currentUser = register();
            case 3 -> {
                System.out.println("Bye!");
                return;
            }
            default -> {
                System.out.println("Invalid option");
                continue;
            }
        }

        if (currentUser == null) {
            System.out.println("Authentication failed");
            continue;
        }

        if (currentUser.getRole().equalsIgnoreCase("ADMIN")) {
            adminMenu(currentUser);
        } else {
            userMenu(currentUser);
        }
    }
    }

    public User login() {

        System.out.print("\nEnter Login: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.login(username, password);

        System.out.println("\nSucces login");
        System.out.println("Welcome " + user.getUserName() + "!");

        return user;
    }

    public User register(){

    System.out.print("Username: ");
    String username = scanner.nextLine();

    System.out.print("Email: ");
    String email = scanner.nextLine();

    System.out.print("Password: ");
    String password = scanner.nextLine();

    User user = new User(username, email, password, "USER");
    return userService.register(user);

    }

    private void adminMenu(User user) {
        while (true) {
            System.out.println("\n=== Admin-Menu ===");
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
                    break;
                case 2:
                    movieController.addMovie();
                    break;
                case 3:
                    movieController.deleteMovie();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option");

            }
        }

    }

    private void userMenu(User user) {
        while (true) {
            System.out.println("\n=== Movie-Search ===");
            System.out.println("""
                    1. Show movies
                    2. Show movies by genre
                    3. Add review
                    4. Show movies review
                    9. Exit
                    """);

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    movieController.showMovies();
                    break;
                case 2:
                    movieController.showMoviesByGenre();
                    break;
                case 3:
                    reviewController.addReview(user);
                    break;
                case 4:
                    reviewController.ShowReviewByMovie();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }

    }
}