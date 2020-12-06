'use struct';

const {describe, it} = require('mocha');
const expect = require('chai').expect;

const dayFour = require('./dayFour')


describe('day four', () => {

    const input = `ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
        byr:1937 iyr:2017 cid:147 hgt:183cm
        
        iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
        hcl:#cfa07d byr:1929
        
        hcl:#ae17e1 iyr:2013
        eyr:2024
        ecl:brn pid:760753108 byr:1931
        hgt:179cm
        
        hcl:#cfa07d eyr:2025 pid:166559648
        iyr:2011 ecl:brn hgt:59in
        `

    it('should create passports from test data', async () => {
        // given
        const filename = './4/testdata.txt';

        // when
        const input = await dayFour.getInput(filename);

        // then
        expect(input.length).to.equal(4)
    });

    it('should pass part one test data', async () => {
        // given
        const filename = './4/testdata.txt';
        const input = await dayFour.getInput(filename);

        // when
        const result = dayFour.getNumberOfValidPassports(input);

        // then
        expect(result).to.equal(2);
    });

})