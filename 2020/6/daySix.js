'use strict';

const {readFile} = require('fs').promises;

const getInput = async (filename) => {
    const input = await readFile(filename, 'utf8');
    return input.toString()
        .trim()
        .split('\n\n')
        .map(group => group
            .replace(/\n$/, '')
            .split('\n')
        );
}

const getUnique = (input) => {
    return input.map(group => {
        const split = group.join('').split('')
        const set = new Set(split);
        const join = Array.from(set).join('');
        return join.replace(/[^a-z]/g, '');
    })
}

const getSumOfUnique = (unique) => {
    return unique.map(x => x.length)
        .reduce((a, b) => a + b, 0);
}

const getSame = (input) => {
    let everyone = 0;
    input.map(group => {
        const members = group.length;
        const answers = group.join('').split('').sort().join('');
        const unique = new Set(answers.split(''))
        for (const letter of unique) {
            const regExp = new RegExp(letter, 'g');
            const count = (answers.match(regExp) || []).length
            if (count === members) {
                everyone++
            }
        }
    });
    return everyone
}

module.exports = {
    getInput,
    getUnique,
    getSumOfUnique,
    getSame
}