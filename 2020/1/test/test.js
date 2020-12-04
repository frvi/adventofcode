'use strict';

const {describe, it} = require('mocha');
const expect = require('chai').expect;
const dayOne = require('../src/index')

describe('day one', () => {
    it('part one test data', () => {
        // given
        const input = [1721, 979, 366, 299, 675, 1456]

        // when
        const result = dayOne.partOne(input);

        // then
        expect(result).to.equal(514579)
    })

    it('part two test data', () => {
        // given
        const input = [1721, 979, 366, 299, 675, 1456]

        // when
        const result = dayOne.partTwo(input);

        // then
        expect(result).to.equal(241861950)
    })
})