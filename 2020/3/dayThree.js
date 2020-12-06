'use strict';

const {readFile} = require('fs').promises;

const tree = '#';

const getInput = async (filename) => {
    const input = await readFile(filename, 'utf8');
    return input.toString()
        .split('\n');
}

const getMapRepetitions = (input, move) => {
    const inputWidth = input[0].length;
    const inputHeight = input.length;
    const stepsDown = inputHeight / move.down;
    const stepsRight = stepsDown * move.right;
    return Math.ceil(stepsRight / inputWidth);
}

const createMap = (input, mapRepetitions) => {
    return input.map(x => x.repeat(mapRepetitions));

}
const treesEncountered = (map, move) => {
    let x = 0;
    let slope = [];
    for (let i = move.down; i < map.length; i += move.down) {
        x += move.right
        slope.push(map[i][x])
    }
    return slope.filter(x => x === tree).length
}

module.exports = {
    getInput,
    getMapRepetitions,
    createMap,
    treesEncountered,
}