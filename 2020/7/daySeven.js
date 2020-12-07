'use strict';

const {readFile} = require('fs').promises;

const getInput = async (filename) => {
    const input = await readFile(filename, 'utf8');
    return input.toString()
        .trim()
        .split('\n')
        .map(line => {
                const strings = line.split(' ');
                const color = `${strings[0]} ${strings[1]}`;
                const content = strings.slice(4)
                const holds = bagsFromContent(content)
                return {
                    color,
                    holds
                }
            }
        );
}

const bagsFromContent = (content) => {
    if (content[0] === 'no') {
        return;
    }
    const size = 4;
    const holds = [];
    for (let i = 0; i < content.length; i += size) {
        const group = content.slice(i, i + size);
        const color = `${group[1]} ${group[2]}`
        const quantity = parseInt(group[0])
        holds.push({color, quantity})
    }
    return holds;
}

let a = 0;

const findContainersFor = (input, bags, total = []) => {
    let containers = [];
    for (const bag of bags) {
        const parents = input.filter(container => {
            if (container.holds) {
                const b = container.holds.some(a => a.color === bag.color);
                return b;
            }
        });
        containers.push(...parents)
    }
    if (containers.length > 0) {
        total.push(...containers)
        return findContainersFor(input, containers, total)
    }
    return [...new Set(total.map(b => b.color))];
}

const findContainedBagsFor = (input, bags, total = []) => {
    let contains = []
    for (const bag of bags) {
        if (bag.holds) {
            bag.holds.map(contained => {
                const found = input.find(bagRule => bagRule.color === contained.color);
                if (!bag.amt) {
                    bag.amt = 1;
                }
                contains.push({
                    ...found,
                    amt: contained.quantity * bag.amt
                })
            });
        }
    }
    if (contains.length > 0) {
        total.push(...contains);
        return findContainedBagsFor(input, contains, total);
    }
    return total;
}

module.exports = {
    getInput,
    bagsFromContent,
    findContainersFor,
    findContainedBagsFor
}