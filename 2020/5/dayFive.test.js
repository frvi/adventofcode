'use strict';

const {describe, it} = require('mocha');
const expect = require('chai').expect;

const dayFive = require('./dayFive');

describe('day five', () => {
    describe('part one', () => {
        it('should convert seat specification to binary', () => {
            // given
            const seat = 'FBFBBFFRLR'

            // when
            const result = dayFive.convertSeatToBinary(seat);

            // then
            expect(result).to.equal('0101100101')
        });

        it('should convert seat specification to binary', () => {
            // given
            const seat = 'FBFBBFFRLR'

            // when
            const result = dayFive.getSeat(seat);

            // then
            expect(result).to.eql({
                row: 44,
                column: 5,
                seatId: 357
            })
        });

        it('should pass part one test data', () => {
            // given
            const input = [
                'BFFFBBFRRR',
                'FFFBBBFRRR',
                'BBFFBBFRLL'
            ];

            // when
            const seats = dayFive.getSeats(input);
            const result = dayFive.getHighestSeatId(seats);


            // then
            expect(seats[0]).to.eql({
                row: 70,
                column: 7,
                seatId: 567
            });
            expect(seats[1]).to.eql({
                row: 14,
                column: 7,
                seatId: 119
            });
            expect(seats[2]).to.eql({
                row: 102,
                column: 4,
                seatId: 820
            });
            expect(result).to.equal(820);
        });

        it('should solve part one', async () => {
            // given
            const filename = './5/input.txt';
            const input = await dayFive.getInput(filename);

            // when
            const seats = dayFive.getSeats(input);
            const result = dayFive.getHighestSeatId(seats)

            // then
            expect(result).to.equal(894);
        });
    });

    describe('part two', () => {
        it('should solve part two', async () => {
            // given
            const filename = './5/input.txt';
            const input = await dayFive.getInput(filename);

            // when
            const seats = dayFive.getSeats(input);
            const result = dayFive.findMySeat(seats);

            // then
            expect(result).to.equal(579);
        });
    })
})