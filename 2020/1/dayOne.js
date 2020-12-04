'use strict';

const fs = require('fs').promises;

const year = 2020;

const getInput = async () => {
    const input = await fs.readFile('./1/input.txt', 'utf8');
    return input.toString()
        .split('\n')
        .map(x => parseInt(x))
        .sort((a, b) => a - b);
}

function isSum2020(input) {
    return entry => {
        const remainder = year - entry;
        if (input.includes(remainder)) {
            return true;
        }
    };
}

const partOne = (input) => {
    return input
        .filter(isSum2020(input))
        .reduce((a, b) => a * b)
}

const partTwo = (input) => {
    const entries = new Set;
    for (const first of input) {
        for (const second of input) {
            const remainder = year - first - second
            if (input.includes(remainder)) {
                entries.add(remainder)
                entries.add(first)
                entries.add(second)
            }
        }
    }
    return Array.from(entries).reduce((a, b) => a * b);
}

const dayOne = async () => {
    const input = await getInput();
    const one = partOne(input);
    const two = partTwo(input);

    console.log(`Dec 1 2020, Part Two: ${one}`);
    console.log(`Dec 1 2020, Part One: ${two}`);

}

module.exports = {
    partOne,
    partTwo
}
