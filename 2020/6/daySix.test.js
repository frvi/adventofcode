'use strict';

const {describe, it} = require('mocha');
const expect = require('chai').expect;

const daySix = require('./daySix');

describe('day six', () => {
    describe('part one', () => {
        it('should read test data', async () => {
            // given
            const filename = './6/testdata_part_one.txt';

            // when
            const input = await daySix.getInput(filename);

            // then
            expect(input.length).to.equal(5)
        });

        it('should convert input to unique groups', async () => {
            // given
            const expected = ['abc', 'abc', 'abc', 'a', 'b'];
            const filename = './6/testdata_part_one.txt';
            const input = await daySix.getInput(filename);

            // when
            const result = daySix.getUnique(input);

            // then
            expect(result).to.eql(expected)
        });


        it('should pass test data', async () => {
            // given
            const expected = ['abc', 'abc', 'abc', 'a', 'b'];
            const filename = './6/testdata_part_one.txt';
            const input = await daySix.getInput(filename);
            const unique = daySix.getUnique(input);

            // when
            const result = daySix.getSumOfUnique(unique);

            // then
            expect(result).to.equal(11);
        });

        it('should solve part one', async () => {
            // given
            const filename = './6/input.txt';
            const input = await daySix.getInput(filename);
            const unique = daySix.getUnique(input);

            // when
            const result = daySix.getSumOfUnique(unique);

            // then
            expect(result).to.equal(6596);
        });
    });

    describe('part two', () => {
        it('should pass test data', async () => {
            // when
            const filename = './6/testdata_part_two.txt';
            const input = await daySix.getInput(filename);

            // when
            const result = daySix.getSame(input);

            //then
            expect(result).to.be.equal(6)
        });

        it('should solve part two', async () => {
            // when
            const filename = './6/input.txt';
            const input = await daySix.getInput(filename);

            // when
            const result = daySix.getSame(input);

            //then
            expect(result).to.be.equal(3219)
        });
    })
});