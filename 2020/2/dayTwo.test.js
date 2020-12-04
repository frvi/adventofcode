'use struct';

const {describe, it} = require('mocha');
const expect = require('chai').expect;

const dayTwo = require('./dayTwo')

describe('day two', () => {
    it('should pass part one test data', function () {
        // given
        const input = [
            ['1-3', 'a', 'abcde'],
            ['1-3', 'b', 'cdefg'],
            ['2-9', 'c', 'ccccccccc']
        ]

        // when
        const result = dayTwo.validate(input);

        // then
        expect(result).to.equal(2)
    });

    it('should create input array from file', async () => {
        // given
        const filename = '2/input.txt'

        // when
        const input = await dayTwo.getInput(filename);

        // then
        expect(input[0]).to.eql(['1-7', 'j', 'vrfjljjwbsv'])
    });

    it('should find amount of valid passwords', async () => {
        // given
        const filename = '2/input.txt'
        const input = await dayTwo.getInput(filename);

        // when
        const result = dayTwo.validate(input);

        // then
        expect(result).to.equal(580);
    });

    it('should pass part two test data', function () {
        // given
        const input = [
            ['1-3', 'a', 'abcde'],
            ['1-3', 'b', 'cdefg'],
            ['2-9', 'c', 'ccccccccc']
        ]

        // when
        const result = dayTwo.validateToboggan(input);

        // then
        expect(result).to.equal(1)
    });

    it('should find amount of valid toboggan passwords', async () => {
        // given
        const filename = '2/input.txt'
        const input = await dayTwo.getInput(filename);

        // when
        const result = dayTwo.validateToboggan(input);

        // then
        expect(result).to.equal(611);
    });
})