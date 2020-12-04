'use strict';

const fs = require('fs').promises;

const year = 2020;

const main = async () => {
    const input = await fs.readFile('1/src/input.txt', 'utf8');
    const entries = input.toString().split('\n').map(x => parseInt(x));
    return entries
        .filter(entry => {
            const remainder = year - entry;
            if (entries.includes(remainder)) {
                return true;
            }
        })
        .reduce((a, b) => a * b)
}

main().then(result => console.log(result));
