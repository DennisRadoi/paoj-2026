package com.pao.laboratory03.enums;

public enum Priority {
    LOW(1, "Green"){
        public String getEmoji(){
            return "🟢";
        }
    },
    MEDIUM(2, "Yellow"){
        public String getEmoji(){
            return "🟡";
        }
    },

    HIGH(3, "Orange"){
        public String getEmoji(){
            return "🟠";
        }
    },

    CRITICAL(4, "Red"){
        public String getEmoji(){
            return "🔴";
        }
    };

    private int level;
    private String color;

    private Priority(int level, String color){
        this.level = level;
        this.color = color;
    }

    public int getLevel() {
        return level;
    }

    public String getColor() {
        return color;
    }

    public abstract String getEmoji();
}
