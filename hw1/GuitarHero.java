import synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        char[] keyboard_ary = keyboard.toCharArray();
        GuitarString[] guitarStrings = new GuitarString[keyboard_ary.length];
        for (int i = 0; i < keyboard_ary.length; i++) {
            guitarStrings[i] = new GuitarString(CONCERT_A * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < keyboard_ary.length; i++) {
                    if (key == keyboard_ary[i]) {
                        guitarStrings[i].pluck();
                    }
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString guitarString : guitarStrings) {
                sample += guitarString.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString guitarString : guitarStrings) {
                guitarString.tic();
            }
        }
    }
}

