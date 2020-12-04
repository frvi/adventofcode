'use struct';

const {describe, it} = require('mocha');
const expect = require('chai').expect;

const dayThree = require('./dayThree')

function createMaps(moves, input) {
    return moves
        .map(move => dayThree.getMapRepetitions(input, move))
        .map(mapRepetitions => dayThree.createMap(input, mapRepetitions));
}

describe('day three', () => {

    const input = [
        '..##.......',
        '#...#...#..',
        '.#....#..#.',
        '..#.#...#.#',
        '.#...##..#.',
        '..#.##.....',
        '.#.#.#....#',
        '.#........#',
        '#.##...#...',
        '#...##....#',
        '.#..#...#.#',
    ]

    const move = {
        right: 3,
        down: 1,
    }

    const moves = [
        {
            right: 1,
            down: 1
        },
        {
            right: 3,
            down: 1
        },
        {
            right: 5,
            down: 1
        },
        {
            right: 7,
            down: 1
        },
        {
            right: 1,
            down: 2
        }
    ]


    it('should pass part one test data', function () {
        // when
        const result = dayThree.getMapRepetitions(input, move);

        // then
        expect(result).to.equal(3)
    });

    it('should create repeated map', () => {
        // given
        const mapRepetitions = 3;

        // when
        const result = dayThree.createMap(input, mapRepetitions)

        // then
        expect(result[0].length).to.equal(33)
    });

    it('should pass part one test data', function () {
        // given
        const mapRepetitions = dayThree.getMapRepetitions(input, move);
        const map = dayThree.createMap(input, mapRepetitions);

        // when
        const result = dayThree.treesEncountered(map, move);

        // then
        expect(result).to.equal(7);
    });

    it('should solve part one', async () => {
        // given
        const filename = './3/input.txt';
        const input = await dayThree.getInput(filename);
        const mapRepetitions = dayThree.getMapRepetitions(input, move);
        const map = dayThree.createMap(input, mapRepetitions);

        // when
        const result = dayThree.treesEncountered(map, move);

        // then
        expect(result).to.equal(184);
    });

    it('should pass part two test data', () => {
        // given
        const maps = createMaps(moves, input);

        // when
        let result = 1;
        for (let i = 0; i < maps.length; i++) {
            result *= dayThree.treesEncountered(maps[i], moves[i]);
        }

        // then
        expect(result).to.equal(336);
    });

    it('should solve part two', async () => {
        // given
        const filename = './3/input.txt';
        const input = await dayThree.getInput(filename);
        const maps = createMaps(moves, input);

        // when
        let result = 1;
        for (let i = 0; i < maps.length; i++) {
            result *= dayThree.treesEncountered(maps[i], moves[i]);
        }

        // then
        expect(result).to.equal(2431272960);
    });

})