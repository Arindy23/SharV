package de.arindy.sharv.api.gui;

import lombok.Getter;

@Getter
public class DefaultConditionMonitorListener implements ConditionMonitorListener {

    private int physicalDamage;
    private int stunDamage;
    private ConditionMonitorView conditionMonitorView;

    @Override
    public ConditionMonitorListener register(ConditionMonitorView conditionMonitorView) {
        this.conditionMonitorView = conditionMonitorView;
        return this;
    }

    @Override
    public void changePhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    @Override
    public void changeStunDamage(int stunDamage) {
        this.stunDamage = stunDamage;
    }

}
