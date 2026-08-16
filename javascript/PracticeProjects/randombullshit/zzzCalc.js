const lvl1mat = 1;
const lvl2mat = lvl1mat*3;
const lvl3mat = lvl2mat*3;

const batteriesHour = 10;
const batteriesWeek = 1680;
const promotion = {
    mats: 1*lvl3mat + 1*lvl2mat,
    batteries: 20,
};
const skill = {
    mats: 1*lvl3mat + 2*lvl2mat,
    batteries: 20,
};
const expert = {
    mats: 5,
    batteries: 40,
};
const hunt = {
    mats: 1,
    batteries: 60,
};
const modification = {
    mats: 1*lvl3mat + 3*lvl2mat,
    batteries: 20,
};

const promotionNeed = {
    11: {dennies: 24000,  mats: lvl1mat*4},
    21: {dennies: 56000,  mats: lvl2mat*12},
    31: {dennies: 120000, mats: lvl2mat*20},
    41: {dennies: 200000, mats: lvl3mat*10},
    51: {dennies: 400000, mats: lvl3mat*20},
};
const skillNeed = {
    2:  {dennies: 2000,   mats: lvl1mat*2},
    3:  {dennies: 3000,   mats: lvl1mat*3},
    4:  {dennies: 6000,   mats: lvl2mat*2},
    5:  {dennies: 9000,   mats: lvl2mat*3},
    6:  {dennies: 12000,  mats: lvl2mat*4},
    7:  {dennies: 18000,  mats: lvl2mat*6},
    8:  {dennies: 45000,  mats: lvl3mat*5},
    9:  {dennies: 67500,  mats: lvl3mat*8},
    10: {dennies: 90000,  mats: lvl3mat*10},
    11: {dennies: 112500, mats: lvl3mat*12},
    12: {dennies: 135000, mats: lvl3mat*15},
};
const coreSkillNeed = {
    2: {dennies: 5000,   expertMats: 0,  huntMats: 0},
    3: {dennies: 12000,  expertMats: 2,  huntMats: 0},
    4: {dennies: 28000,  expertMats: 4,  huntMats: 0},
    5: {dennies: 60000,  expertMats: 9,  huntMats: 2},
    6: {dennies: 100000, expertMats: 15, huntMats: 3},
    7: {dennies: 200000, expertMats: 30, huntMats: 4},
};
const modNeed = {
    A: {
        11: {dennies: 9600,  mats: lvl1mat*3},
        21: {dennies: 22400,  mats: lvl2mat*10},
        31: {dennies: 48000, mats: lvl2mat*16},
        41: {dennies: 80000, mats: lvl3mat*8},
        51: {dennies: 160000, mats: lvl3mat*16},
    },
    S: {
        11: {dennies: 12000,  mats: lvl1mat*4},
        21: {dennies: 28000,  mats: lvl2mat*12},
        31: {dennies: 60000, mats: lvl2mat*20},
        41: {dennies: 100000, mats: lvl3mat*10},
        51: {dennies: 200000, mats: lvl3mat*20},
    },
};

function calcAgent(currentLevel, targetLevel) {
    if (currentLevel < 1 || currentLevel >= targetLevel || targetLevel > 60) {
        return "Error: Invalid levels";
    }

    let total = {
        dennies: 0,
        mats: {total: 0, lvl3: 0, lvl2: 0, lvl1: 0},
        promotionMats: {total: 0, lvl3: 0, lvl2: 0, lvl1: 0},
        batteries: 0,
    };

    for (let level = currentLevel + 1; level <= targetLevel; level++) {
        //regular agent upgrade materials here

        if (level % 10 === 1 && level <= 51) {
            total.promotionMats.total += promotionNeed[level].mats;
            total.dennies += promotionNeed[level.dennies];
        }
    }
    let remainder = total.promotionMats.total % 9;
    let remainder2 = remainder % 3;
    total.promotionMats.lvl3 = (total.promotionMats.total - remainder) / 9;
    total.promotionMats.lvl2 = (remainder - remainder2) / 3;
    total.promotionMats.lvl1 = remainder2;

    let levelBatteries = 0;
    let promotionBatteries = Math.ceil(total.promotionMats.total/promotion.mats) * promotion.batteries;
    total.batteries = levelBatteries + promotionBatteries;

    return total;
}
function calcSkill(currentLevel, targetLevel) {
    if (currentLevel < 1 || currentLevel >= targetLevel || targetLevel > 12) {
        return "Error: Invalid levels";
    }

    let total = {
        dennies: 0,
        mats: {
            total: 0,
            lvl3: 0,
            lvl2: 0,
            lvl1: 0,
        },
        batteries: 0,
    };

    for (let level = currentLevel + 1; level <= targetLevel; level++) {
        total.mats.total += skillNeed[level].mats;
        total.dennies += skillNeed[level].dennies;
    }
    let remainder = total.mats.total % 9;
    let remainder2 = remainder % 3;
    total.mats.lvl3 = (total.mats.total - remainder) / 9;
    total.mats.lvl2 = (remainder - remainder2) / 3;
    total.mats.lvl1 = remainder2;

    total.batteries = Math.ceil(total.mats.total/skill.mats) * skill.batteries;

    return total;
}
function calcCoreSkill(currentLevel, targetLevel) {
    if (currentLevel < 1 || currentLevel >= targetLevel || targetLevel > 7) {
        return "Error: Invalid levels";
    }
    let total = {
        dennies: 0,
        expertMats: 0,
        huntMats: 0,
        batteries: 0,
    };

    for (let level = currentLevel + 1; level <= targetLevel; level++) {
        total.dennies += coreSkillNeed[level].dennies;
        total.expertMats += coreSkillNeed[level].expertMats;
        total.huntMats += coreSkillNeed[level].huntMats;
    }

    let expertBatteries = Math.ceil(total.expertMats/expert.mats) * expert.batteries;
    let huntBatteries = Math.ceil(total.huntMats/hunt.mats) * hunt.batteries;
    total.batteries = expertBatteries + huntBatteries;

    return total;
}
function calcEngine(rank, currentLevel, targetLevel) {
    if (currentLevel < 1 || currentLevel >= targetLevel || targetLevel > 60) {
        return "Error: Invalid levels";
    }

    let total = {
        dennies: 0,
        mats: {total:0, lvl3: 0, lvl2: 0, lvl1: 0},
        modMats: {total:0, lvl3: 0, lvl2: 0, lvl1: 0},
        batteries: 0,
    };

    for (let level = currentLevel + 1; level <= targetLevel; level++) {
        //regular engine upgrade materials here
        
        if (level % 10 === 1 && level <= 51) {
            total.modMats.total += modNeed[rank][level].mats;
            total.dennies += modNeed[rank][level].dennies;
        } 
    }

    let remainder = total.modMats.total % 9;
    let remainder2 = remainder % 3;
    total.modMats.lvl3 = (total.modMats.total - remainder) / 9;
    total.modMats.lvl2 = (remainder - remainder2) / 3;
    total.modMats.lvl1 = remainder2;

    let levelBatteries = 0;
    let modificationBatteries = Math.ceil(total.modMats.total/modification.mats) * modification.batteries;
    total.batteries = levelBatteries + modificationBatteries;

    return total;
}
