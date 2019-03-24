package de.arindy.sharv.api.gui;

public interface ConditionMonitorView {

    ConditionMonitorView setPhysicalDamageMax(int physicalDamageMax);

    ConditionMonitorView setPhysicalDamage(int physicalDamage);

    ConditionMonitorView setStunDamageMax(int stunDamageMax);

    ConditionMonitorView setStunDamage(int stunDamage);

    ConditionMonitorView setDicePoolModifier(int dicePoolModifier);

    ConditionMonitorView registerListener(ConditionMonitorListener conditionMonitorListener);

}
