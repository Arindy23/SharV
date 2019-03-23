package de.arindy.sharv.api.gui;

public interface AttributesView {
    
    AttributesView setBodyMax(int bodyMax);

    AttributesView setBodyEffective(int bodyEffective);

    AttributesView setAgilityMax(int agilityMax);

    AttributesView setAgilityEffective(int agilityEffective);

    AttributesView setReactionMax(int reactionMax);

    AttributesView setReactionEffective(int reactionEffective);

    AttributesView setStrengthMax(int strengthMax);

    AttributesView setStrengthEffective(int strengthEffective);

    AttributesView setWillpowerMax(int willpowerMax);

    AttributesView setWillpowerEffective(int willpowerEffective);

    AttributesView setLogicMax(int logicMax);

    AttributesView setLogicEffective(int logicEffective);

    AttributesView setIntuitionMax(int intuitionMax);

    AttributesView setIntuitionEffective(int intuitionEffective);

    AttributesView setCharismaMax(int charismaMax);

    AttributesView setCharismaEffective(int charismaEffective);

    AttributesView setComposure(int composure);

    AttributesView setJudgeIntentions(int judgeIntentions);

    AttributesView setMemory(int memory);

    AttributesView setLiftCarry(int liftCarry);

    AttributesView setWalk(int walk);

    AttributesView setRun(int run);

    AttributesView setSprint(int sprint);

    AttributesView setInitiativePhysical(int initiativePhysical);

    AttributesView setInitiativeAstral(int initiativeAstral);

    AttributesView setInitiativeMatrixAR(int initiativeMatrixAR);

    AttributesView setInitiativeMatrixVRcold(int initiativeMatrixVRcold);

    AttributesView setInitiativeMatrixVRhot(int initiativeMatrixVRhot);

    AttributesView setInitiativeRiggingAR(int initiativeRiggingAR);

    AttributesView setInitiativeRiggingVRcold(int initiativeRiggingVRcold);

    AttributesView setInitiativeRiggingVRhot(int initiativeRiggingVRhot);

    AttributesView setInitiativeRiggingDirect(int initiativeRiggingDirect);

    AttributesView setInitiativePhysicalDice(int initiativePhysicalDice);

    AttributesView setInitiativePhysicalDice(int initiativePhysicalDice, boolean highlight);

    AttributesView setInitiativeAstralDice(int initiativeAstralDice);

    AttributesView setInitiativeAstralDice(int initiativeAstralDice, boolean highlight);

    AttributesView setInitiativeMatrixARDice(int initiativeMatrixARDice);

    AttributesView setInitiativeMatrixARDice(int initiativeMatrixARDice, boolean highlight);

    AttributesView setInitiativeMatrixVRcoldDice(int initiativeMatrixVRcoldDice);

    AttributesView setInitiativeMatrixVRcoldDice(int initiativeMatrixVRcoldDice, boolean highlight);

    AttributesView setInitiativeMatrixVRhotDice(int initiativeMatrixVRhotDice);

    AttributesView setInitiativeMatrixVRhotDice(int initiativeMatrixVRhotDice, boolean highlight);

    AttributesView setInitiativeRiggingARDice(int initiativeRiggingARDice);

    AttributesView setInitiativeRiggingARDice(int initiativeRiggingARDice, boolean highlight);

    AttributesView setInitiativeRiggingVRcoldDice(int initiativeRiggingVRcoldDice);

    AttributesView setInitiativeRiggingVRcoldDice(int initiativeRiggingVRcoldDice, boolean highlight);

    AttributesView setInitiativeRiggingVRhotDice(int initiativeRiggingVRhotDice);

    AttributesView setInitiativeRiggingVRhotDice(int initiativeRiggingVRhotDice, boolean highlight);

    AttributesView setInitiativeRiggingDirectDice(int initiativeRiggingDirectDice);

    AttributesView setInitiativeRiggingDirectDice(int initiativeRiggingDirectDice, boolean highlight);

    AttributesView setEssence(int essence);

    AttributesView setPhysicalLimit(int physicalLimit);

    AttributesView setMentalLimit(int mentalLimit);

    AttributesView setSocialLimit(int socialLimit);

    AttributesView removeSpecials();

    AttributesView addSpecials(String... specials);

    AttributesView setSpecial(String special);

    AttributesView setSpecialValue(int specialValue);

    AttributesView setBody(int body);

    AttributesView setAgility(int agility);

    AttributesView setReaction(int reaction);

    AttributesView setStrength(int strength);

    AttributesView setWillpower(int willpower);

    AttributesView setLogic(int logic);

    AttributesView setIntuition(int intuition);

    AttributesView setCharisma(int charisma);

    AttributesView setEdge(int edge);

    AttributesView setBurnedEdge(int burndeEdge);

    AttributesView registerListener(AttributesListener attributesListener);

}
