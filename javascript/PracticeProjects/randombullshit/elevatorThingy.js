const prompt = require('prompt-sync')({ sigint: true });

class Elevator {
    name;
    position;
    direction;
    destinations;
};

class Floor {
    number;
    requests;
}

class Request {
    floor;
    destination;
}

let elevators = [
    new Elevator("A", 0, 0, []),
    new Elevator("B", 0, 0, []),
    new Elevator("C", 0, 0, []),
];

let floors = [
    new Floor(0, []),
    new Floor(1, []),
    new Floor(2, []),
    new Floor(3, []),
    new Floor(4, []),
    new Floor(5, []),
    new Floor(6, []),
    new Floor(7, []),
    new Floor(8, []),
    new Floor(9, []),
    new Floor(10, []),
];

function begin() {
    console.log("Elevator dispatch system simulation.");
    console.log("Type in requests in this format [1 2]");
    console.log("where 1 is where the request comes from and 2 is the destination");
    console.log("Press Enter with nothing typed in to avance one step");

    handleInput();
}

function handleInput() {
    let input = prompt();
    const command = parseInput(input);

    switch (command.type) {
    case "step":
        step();
        break;
    case "request":
        makeRequest(command);
        break;
    }
}

function parseInput(input) {
    // turn an input like [6 7] into a command object
}

function makeRequest(command) {
    // take the command and turn it into a request
}

function step() {
    // advance the simulation one step
}
