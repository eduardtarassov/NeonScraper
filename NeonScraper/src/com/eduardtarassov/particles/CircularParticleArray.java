package com.eduardtarassov.particles;

/**
 * Created by Eduard on 10/04/14.
 */
public class CircularParticleArray {
        private Particle[] list;
        private int start;

        public CircularParticleArray(int capacity) {
            list = new Particle[capacity];
        }

        public void setStart(int start) {
            this.start = start % list.length;
        }

        public int getStart() {
            return start;
        }

        public int getCount() {
            int count = 0;
            for (int i = 0; i < list.length; i++)
                if (list[i] != null)
                    count++;
            return count;
        }

        public int getCapacity(){
            return list.length;
        }

        public void setElement(Particle value, int i){
            list[(start + i) % list.length] = value;
        }

        public Particle getElement(int i){
            return list[(start + i) % list.length];
        }
}
