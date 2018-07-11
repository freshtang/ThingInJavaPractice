package com.tjj.chapter7;



class EventSet {
    private Event[] events = new Event[100];
    private int index = 0;
    private int next = 0;
    public void add(Event e) {
        if(index >= events.length)
            return; // (In real life, throw exception)
        events[index++] = e;
    }
    public Event getNext() {
        boolean looped = false;
        int start = next;
        do {
            next = (next + 1) % events.length;
            // See if it has looped to the beginning:
            if(start == next) looped = true;
            // If it loops past start, the list
            // is empty:
            if((next == (start + 1) % events.length)
                    && looped)
                return null;
        } while(events[next] == null);
        return events[next];
    }
    public void removeCurrent() {
        events[next] = null;
    }
}



public class GreenhouseControls
        extends Controller {
    private boolean light = false;
    private boolean water = false;
    private boolean fan = false;
    private String thermostat = "Day";
    private class FanOn extends Event {
        public FanOn(long eventTime) {
            super(eventTime);
        }
        public void action() {
            // Put hardware control code here to
            // physically turn on the light.
            fan = true;
        }
        public String description() {
            return "Fan is on";
        }
    }
    private class FanOff extends Event {
        public FanOff(long eventTime) {
            super(eventTime);
        }
        public void action() {
            // Put hardware control code here to
            // physically turn on the light.
            fan = false;
        }
        public String description() {
            return "Fan is off";
        }
    }
    private class LightOn extends Event {
        public LightOn(long eventTime) {
            super(eventTime);
        }
        public void action() {
            // Put hardware control code here to
            // physically turn on the light.
            light = true;
        }
        public String description() {
            return "Light is on";
        }
    }
    private class LightOff extends Event {
        public LightOff(long eventTime) {
            super(eventTime);
        }
        public void action() {
            // Put hardware control code here to
            // physically turn off the light.
            light = false;
        }
        public String description() {
            return "Light is off";
        }
    }
    private class WaterOn extends Event {
        public WaterOn(long eventTime) {
            super(eventTime);
        }
        public void action() {
            // Put hardware control code here
            water = true;
        }
        public String description() {
            return "Greenhouse water is on";
        }
    }
    private class WaterOff extends Event {
        public WaterOff(long eventTime) {
            super(eventTime);
        }
        public void action() {
            // Put hardware control code here
            water = false;
        }
        public String description() {
            return "Greenhouse water is off";
        }
    }
    private class ThermostatNight extends Event {
        public ThermostatNight(long eventTime) {
            super(eventTime);
        }
        public void action() {
            // Put hardware control code here
            thermostat = "Night";
        }
        public String description() {
            return "Thermostat on night setting";
        }
    }
    private class ThermostatDay extends Event {
        public ThermostatDay(long eventTime) {
            super(eventTime);
        }
        public void action() {
            // Put hardware control code here
            thermostat = "Day";
        }
        public String description() {
            return "Thermostat on day setting";
        }
    }
    // An example of an action() that inserts a
    // new one of itself into the event list:
    private int rings;
    private class Bell extends Event {
        public Bell(long eventTime) {
            super(eventTime);
        }
        public void action() {
            // Ring bell every 2 seconds, rings times:
            System.out.println("Bing!");
            if(--rings > 0)
                addEvent(new Bell(
                        System.currentTimeMillis() + 2000));
        }
        public String description() {
            return "Ring bell";
        }
    }
    private class Restart extends Event {
        public Restart(long eventTime) {
            super(eventTime);
        }
        public void action() {
            long tm = System.currentTimeMillis();
            // Instead of hard-wiring, you could parse
            // configuration information from a text
            // file here:
            rings = 5;
            addEvent(new ThermostatNight(tm));
            addEvent(new LightOn(tm + 1000));
            addEvent(new LightOff(tm + 2000));
            addEvent(new FanOn(tm + 3000));
            addEvent(new FanOff(tm + 4000));
//            addEvent(new WaterOn(tm + 3000));
//            addEvent(new WaterOff(tm + 8000));
//            addEvent(new Bell(tm + 9000));
//            addEvent(new ThermostatDay(tm + 10000));
            // Can even add a Restart object!
        addEvent(new Restart(tm + 5000));
        }
        public String description() {
            return "Restarting system";
        }
    }
    public static void main(String[] args) {
        GreenhouseControls gc =
                new GreenhouseControls();
        long tm = System.currentTimeMillis();
        gc.addEvent(gc.new Restart(tm));
        gc.run();
    }
}
