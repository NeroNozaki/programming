const prompt = require('prompt-sync')({ sigint: true });

let playerScore = 0;
let computerScore = 0;
let game = false;
let round = -1;

main();

function main() {
    let start = prompt("Let's play rock, paper scissors? (y/n) ").trim().toLowerCase(); 
    if (start != "y") {
        console.log("Maybe next time ;-;");
        return;
    } else {
        console.log("let's go then :)");
        new_game();
    } 
    game = false;
    score();
    console.log("See you next time!");
}

function new_game() {
    round++;
    while (true) {
        let player;
        game = true;
        let computer = Math.floor(Math.random() * 3) + 1;
        if (round > 0) {
            score();
        }
        console.log("\n(1 - Rock)");
        console.log("(2 - Paper)");
        console.log("(3 - Scissors)");
        while (true) {
            player = prompt("Rock, paper or scissors? ").trim();
            player = Number(player);
            if (isNaN(player) || player < 1 || player > 3) {
                console.log("Please input either 1, 2 or 3."); 
            } else {
                break;
            }
        }

        console.log("\n");
        if (player == computer) {
            choices(computer, player);
            console.log("i guess we tied :/");
        } else if (player == 1 && computer == 2 ||
                   player == 2 && computer == 3 ||
                   player == 3 && computer == 1) {
            computerScore++;
            choices(computer, player);
            console.log("i guess i won :D");
        } else {
            playerScore++;
            console.log("i lost? D:");
        }

        let again = prompt("Would you like to play again? (y/n) ");
        if (again != "y") {
            break;
        } else {
            console.log("Let's go!");
        }
    }
}

function translate(number) {
    if (number == 1) {
        return "Rock";
    } else if (number == 2) {
        return "Paper";
    } else {
        return "Scissors";
    }
}

function choices(arg1, arg2) {
    console.log(`My choice: ${translate(arg1)}`);
    console.log(`Your choice: ${translate(arg2)}`);
}

function score() {
    if (game == true) {
        console.log("\nOur current scores: ");
        console.log(`Your score: ${playerScore}`);
        console.log(`My score: ${computerScore}`);
        if (playerScore == computerScore) {
            console.log("We're neck a neck!");
        } else if (playerScore < computerScore) {
            console.log("I'm winning :)");
        } else {
            console.log("You're winning :(");
        }
    } else if (game == false) {
        console.log("\nThe final scores: ");
        console.log(`Your score: ${playerScore}`);
        console.log(`My score: ${computerScore}`);
        if (playerScore == computerScore) {
            console.log("We tied.");
        } else if (playerScore < computerScore) {
            console.log("I beat you! :D");
        } else {
            console.log("You beat me. D:");
        }
    }
}



