'use strict';

const {readFile} = require('fs/promises')

const getInput = async (filename) => {
    const input = await readFile(filename, 'utf8');
    return input.toString()
        .split('\n\n')
        .map(p => p.replace(/\n/g, ' '))
        .map(s => {
            const {groups: {byr}} = /.*byr:(?<byr>[0-9]{4}).*/.exec(s) || {groups: {}};
            const {groups: {iyr}} = /.*iyr:(?<iyr>[0-9]{4}).*/.exec(s) || {groups: {}};
            const {groups: {eyr}} = /.*eyr:(?<eyr>[\d]{4}).*/.exec(s) || {groups: {}};
            const {groups: {hgt}} = /.*hgt:(?<hgt>[\d]{1,3}(cm|in)).*/.exec(s) || {groups: {}};
            const {groups: {hcl}} = /.*hcl:(?<hcl>#[a-z0-9].*).*/.exec(s) || {groups: {}};
            const {groups: {ecl}} = /.*ecl:(?<ecl>[a-z]{3}).*/.exec(s) || {groups: {}};
            const {groups: {pid}} = /.*pid:(?<pid>[0-9]{9}).*/.exec(s) || {groups: {}};
            const {groups: {cid}} = /.*cid:(?<cid>[0-9]{3}).*/.exec(s) || {groups: {}};
            return {byr, iyr, eyr, hgt, hcl, ecl, pid, cid}
        })
}

const getNumberOfValidPassports = (input) => {
    return input
        .filter(passport => passport.byr !== undefined)
        .filter(passport => passport.iyr !== undefined)
        .filter(passport => passport.eyr !== undefined)
        .filter(passport => passport.hgt !== undefined)
        .filter(passport => passport.hcl !== undefined)
        .filter(passport => passport.ecl !== undefined)
        .filter(passport => passport.pid !== undefined)
        .length

}


module.exports = {
    getInput,
    getNumberOfValidPassports
}