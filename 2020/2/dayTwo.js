'use strict';

const fs = require('fs').promises;

const getInput = async (filename) => {
    const input = await fs.readFile(filename, 'utf8');
    const lines = input.toString().split('\n');
    return lines
        .map(line => line.split(' '))
        .map(line => {
            const range = line[0];
            const character = line[1].slice(0, -1);
            const password = line[2];
            return [range, character, password];
        })
}

const validate = (input) => {
    return input.filter(entry => {
        const [range, character, password] = entry;
        const [min, max] = range.split('-').map(x => parseInt(x))
        const occurrence = password.split('').filter(x => x === character).length;
        return occurrence >= min && occurrence <= max;
    }).length
}


const validateToboggan = (input) => {
    return input.filter(entry => {
        const [range, character, password] = entry;
        const [first, last] = range.split('-').map(x => parseInt(x) - 1)
        const occurrence = password.split('');
        return occurrence[first] === character ^ occurrence[last] === character;
    }).length
}

module.exports = {
    getInput,
    validate,
    validateToboggan
}
