'use strict';

const {readFile} = require('fs/promises')

const getInput = async (filename) => {
    const input = await readFile(filename, 'utf8');
    return input.toString()
        .split('\n\n')
        .map(p => p.replace(/\n/g, ' '))
        .map(s => {
            const {groups: {byr}} = /.*byr:(?<byr>[\S]*).*/.exec(s) || {groups: {}};
            const {groups: {iyr}} = /.*iyr:(?<iyr>[\S]*).*/.exec(s) || {groups: {}};
            const {groups: {eyr}} = /.*eyr:(?<eyr>[\S]*).*/.exec(s) || {groups: {}};
            const {groups: {hgt}} = /.*hgt:(?<hgt>[\S]*).*/.exec(s) || {groups: {}};
            const {groups: {hcl}} = /.*hcl:(?<hcl>[\S]*).*/.exec(s) || {groups: {}};
            const {groups: {ecl}} = /.*ecl:(?<ecl>[\S]*).*/.exec(s) || {groups: {}};
            const {groups: {pid}} = /.*pid:(?<pid>[\S]*).*/.exec(s) || {groups: {}};
            const {groups: {cid}} = /.*cid:(?<cid>[\S]*).*/.exec(s) || {groups: {}};
            return {
                byr: parseInt(byr),
                iyr: parseInt(iyr),
                eyr: parseInt(eyr),
                hgt,
                hcl,
                ecl,
                pid,
                cid}
        })
}

const getPassportsWithNeededData = (input) => {
    return input
        .filter(passport => passport.byr !== undefined)
        .filter(passport => passport.iyr !== undefined)
        .filter(passport => passport.eyr !== undefined)
        .filter(passport => passport.hgt !== undefined)
        .filter(passport => passport.hcl !== undefined)
        .filter(passport => passport.ecl !== undefined)
        .filter(passport => passport.pid !== undefined)
}

const validate = (passports) => {
    return passports
        .filter(passport => passport.byr >= 1920 && passport.byr <= 2002)
        .filter(passport => passport.iyr >= 2010 && passport.iyr <= 2020)
        .filter(passport => passport.eyr >= 2020 && passport.eyr <= 2030)
        .filter(passport => {
            const {groups: {number, unit}} = /(?<number>[\d]{1,3})(?<unit>(in|cm))/.exec(passport.hgt)
            return unit === 'cm' && number >= 150 && number <= 193 ? true : unit === 'in' && number >= 59 && number <= 76;
        })
        .filter(passport => /#[0-9a-f]{6}/.test(passport.hcl))
        .filter(passport => /(amb|blu|brn|gry|grn|hzl|oth)/.test(passport.ecl))
        .filter(passport => /[\d]{9}/.test(passport.pid))
}


module.exports = {
    getInput,
    getPassportsWithNeededData,
    validate
}