package de.arindy.sharv.api.gui;

public interface ConditionMonitorListener {

    ConditionMonitorListener register(ConditionMonitorView conditionMonitorView);

    void changePhysicalDamage(int physicalDamage);

    void changeStunDamage(int stunDamage);

}
