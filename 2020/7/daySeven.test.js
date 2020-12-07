'use strict';

const {describe, it} = require('mocha');
const expect = require('chai').expect;

const daySeven = require('./daySeven');

describe('day seven', () => {
    describe('part one', () => {
        it('should read test data', async () => {
            // given
            const filename = './7/testdata.txt';

            // when
            const input = await daySeven.getInput(filename);

            // then
            expect(input.length).to.equal(9);
            expect(input[0].color).to.equal('light red');
            expect(input[0].holds.length).to.equal(2);
            expect(input[0].holds[0].color).to.equal('bright white');
            expect(input[0].holds[1].color).to.equal('muted yellow');
        })

        it('should create bags from array', function () {
            // given
            const content = [
                '5', 'faded',
                'blue', 'bags,',
                '6', 'dotted',
                'black', 'bags.'
            ];

            // when
            const result = daySeven.bagsFromContent(content);

            // then
            expect(result.length).to.equal(2);
            expect(result[0].color).to.equal('faded blue');
            expect(result[1].color).to.equal('dotted black');
        });

        it('should pass test data', async () => {
            // given
            const filename = './7/testdata.txt';
            const input = await daySeven.getInput(filename);
            const color = 'shiny gold';
            const bags = [{color}]

            // when
            const result = daySeven.findContainersFor(input, bags);

            //then
            expect(result.length).to.equal(4)
        });

        it('should solve part one', async () => {
            // given
            const filename = './7/input.txt';
            const input = await daySeven.getInput(filename);
            const color = 'shiny gold';
            const bags = [{color}]

            // when
            const result = daySeven.findContainersFor(input, bags);


            //then
            expect(result.length).to.equal(222)
        });
    })

    describe('part two', () => {
        it('should pass test data', async () => {
            // given
            const filename = './7/testdata_part_two.txt';
            const input = await daySeven.getInput(filename);
            const color = 'shiny gold';
            const bags = [{
                color,
                holds: [{
                    color: 'dark red',
                    quantity: 2
                },
                ]
            }]

            // when
            const containedBags = daySeven.findContainedBagsFor(input, bags);

            // then
            const result = containedBags.map(bag => bag.amt).reduce((a, b) => a + b);
            expect(result).to.equal(126);
        });

        it('should should solve part two', async () => {
            // given
            const filename = './7/input.txt';
            const bagColor = 'shiny gold';
            const input = await daySeven.getInput(filename);

            const bags = [input.find(bag => bag.color === bagColor)]

            // when
            const containedBags = daySeven.findContainedBagsFor(input, bags);

            // then
            const result = containedBags.map(bag => bag.amt).reduce((a, b) => a + b);
            expect(result).to.equal(13264);
        });
    })
})