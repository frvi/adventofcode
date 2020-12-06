'use strict';

const {readFile} = require('fs/promises')

const emptyGroups = {groups: {}};

const getInput = async (filename) => {
    const input = await readFile(filename, 'utf8');
    return input.toString()
        .split('\n\n')
        .map(p => p.replace(/\n/g, ' '))
        .map(s => {
            const {groups: {byr}} = /.*byr:(?<byr>[\S]*).*/.exec(s) || emptyGroups;
            const {groups: {iyr}} = /.*iyr:(?<iyr>[\S]*).*/.exec(s) || emptyGroups;
            const {groups: {eyr}} = /.*eyr:(?<eyr>[\S]*).*/.exec(s) || emptyGroups;
            const {groups: {hgt}} = /.*hgt:(?<hgt>[\S]*).*/.exec(s) || emptyGroups;
            const {groups: {hcl}} = /.*hcl:(?<hcl>[\S]*).*/.exec(s) || emptyGroups;
            const {groups: {ecl}} = /.*ecl:(?<ecl>[\S]*).*/.exec(s) || emptyGroups;
            const {groups: {pid}} = /.*pid:(?<pid>[\S]*).*/.exec(s) || emptyGroups;
            const {groups: {cid}} = /.*cid:(?<cid>[\S]*).*/.exec(s) || emptyGroups;
            return {
                byr,
                iyr,
                eyr,
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
        .map(passport => {
            return {
                ...passport,
                byr: parseInt(passport.byr),
                iyr: parseInt(passport.iyr),
                eyr: parseInt(passport.eyr)
            }
        })
        .filter(passport => parseInt(passport.byr) >= 1920 && parseInt(passport.byr) <= 2002)
        .filter(passport => parseInt(passport.iyr) >= 2010 && parseInt(passport.iyr) <= 2020)
        .filter(passport => parseInt(passport.eyr) >= 2020 && parseInt(passport.eyr) <= 2030)
        .filter(passport => {
            const {groups: {number, unit}} = /(?<number>[\d]{1,3})(?<unit>(in|cm))/.exec(passport.hgt) || emptyGroups
            return unit === 'cm' && number >= 150 && number <= 193 ? true : unit === 'in' && number >= 59 && number <= 76;
        })
        .filter(passport => /#[0-9a-f]{6}/.test(passport.hcl))
        .filter(passport => /(amb|blu|brn|gry|grn|hzl|oth)/.test(passport.ecl))
        .filter(passport => passport.pid.length === 9 && /[\d]{9}/.test(passport.pid))
}


module.exports = {
    getInput,
    getPassportsWithNeededData,
    validate
}