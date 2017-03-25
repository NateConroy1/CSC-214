package assignment06.csc214.model;

import java.util.ArrayList;

/**
 * Created by Nate on 3/22/17.
 */

public class RunnerManager {

    private static RunnerManager mRunnerManager;
    ArrayList<Runner> mList;

    private RunnerManager() {
        mList = new ArrayList<>();
        addRunner("Nate Conroy", 3, "Camillus, NY", "Steeplechase", false, "Attended West Genesee HS where he lettered all four years on the cross country and indoor and outdoor track and field teams...Was co-captain as a senior on all three squads...Was Wildcats XC MVP as a junior and indoor and outdoor track and field MVP as a senior...Was 2014 Section III champion on the 4x800m relay team while helping 2013 XC team earn a bid to compete at the NYS Federation Championships...Member of the National Honor Society.");
        addRunner("Chris Cook", 4, "Pittsford, NY", "800/1500", false, "Attended Pittsford Mendon HS where he lettered in cross country, track and field and skiing for the Vikings...Named Team captain of the cross country team while also being the Most Improved Player in his senior season...Was an All-County and Section V champion on the track team.");
        addRunner("Chris Dalke", 2, "North Andover, MA", "800", false, "Attended North Andover HS where he lettered in cross country along with indoor and outdoor track and field for the Knights...Earned three XC letters and four in track...Named team captain of indoor and outdoor track and field teams as a senior...Earned 2015 outdoor track Unsung Hero award...Was a 3-time All-Conference selection in the 4x800m...Holds school record for 600m indoors (1:24.91)...Qualified for nationals in the 4x800m outdoors in 2013-15, indoors in 2014...Was relay state champion three times, also helping team capture state title in outdoor track in 2015...Member of the National Honor Society.");
        addRunner("Ankur Desai", 2, "Rome, NY", "10k", false, "Graduated from Rome Free Academy where he participated in cross country, indoor and outdoor track and field and swimming for the Black Knights...Named captain of XC and both track and field squads as a senior...Was 2014-15 League All-Star in the steeplechase.");
        addRunner("Andrew Faulstich", 2, "Wibraham, MA", "5k", false, "Attended Wilbraham and Monson Academy where he earned varsity letters in cross country (4x), outdoor track (4x), indoor track (3x) and swimming (1x)...Was team captain in XC and both track and field squads his junior and senior years...Named team MVP of cross country and outdoor track as a junior, also earning indoor track coaches award...Earned coaches award as a senior in XC and indoor track...Was honored with Trustee Award for leadership, academic and athletic achievement...Also, earned Markell and Monson Class of 1898 Award and Owen David Dow Memorial Award.");
        addRunner("Eric Franklin", 4, "Mt. Laurel, NJ", "Steeplechase", true, "Cross country, indoor track and outdoor track and field letterwinner at Bishop Eustace HS...Named co-captain in all three sports as a senior...Named 1st Team All-Conference three times in both cross country and outdoor track...Named All-South Jersey in XC as a senior...Helped Crusaders teams to conference and non public B state titles as a sophomore and junior in cross country...Personal best time of 15:43.89 in the 5k on the track...Class validictorian and president of the National Honor Society.");
        addRunner("Ivan Frantz", 1, "Hingham, MA", "1500", false, "Earned varsity letters in cross country, indoor and outdoor track and field and sailing at Hingham HS...Captained track and field and cross country teams as a senioir...Was a 2-time Patriot League All-Star in cross country, also earning awards in both tracks as a senior...Personal best 5k time of 16:23...Member of the National Honor Society.");
        addRunner("Jake Greenberg", 4, "Glencoe, IL", "10k", false, "Graduated from New Trier HS where he earned two varsity letters in both cross country and track and field...Earned the Ben Almagauer Most Dedicated Athlete award as a senior...Earned two All-Conference honors both cross country and track and field...Was 2011 conference champion in outdoor 4x800m relay...Holds HS field house record in the mile (4:42.50).");
        addRunner("Andrew Gutierrez", 2, "Simsbury, CT", "400/800", false, "Lettered in cross country, indoor and outdoor track and field for the Trojans of Simsbury HS...Earned All-Conference accolades in all three sports...Named team captain as a senior.");
        addRunner("Collin Gwilt", 1, "Liverpool, NY", "800", false, "Graduated from Liverpool HS...Earned varsity letters in cross country, indoor track and field and outdoor track and field...Named co-captain on cross country team and earned Coaches Award...Named to All-Central NY team...Personal best of 1:59.46 in the 800m and 4:48.45 in the mile...Team posted sectional wins in cross country and indoor track and were state champions in cross country in 2015.");
        addRunner("Forrest Hangen", 2, "Stow, MA", "5k", false, "Earned multiple letters running cross country, indoor and outdoor track and field for Nashoba Regional HS...Named team captain of XC team as a senior...Was a 3-time cross country Midland-Wachusett league All-Star...Named to Freshman All-State team...Personal best time of 16:25 in 5k XC and 4:28 in the mile.");
        addRunner("Jeremy Hartse", 2, "Santa Fe, NM", "5k", false, "Attended Desert Academy in Sante Fe, New Mexico...Earned four letters in cross country and track and field for the Wildcats...Named XC captain in his junior and senior year while leading track team as a senior...Member of the National Honor Society.");
        addRunner("Ben Martell", 2, "Lexington, MA", "Steeplechase", false, "Lexington HS graduate...Lettered in cross country, indoor and outdoor track and field for the Minutemen...Named co-captain of all three teams his senior season...Was League All-Star in XC his sophomore to senior seasons, All-Star in both track and field teams for two seasons...Helped team to three straight cross-country league titles and indoor track team to 2015 Eastern Mass. D1 Championship...Member of the National Honor Society.");
        addRunner("Alphonse Mugisha", 4, "Syracuse, NY", "5k", false, "Graduated from Nottingham HS where he earned letters in cross country along with indoor and outdoor track and field for the Bulldogs...Named cross country co-captain his junior and senior seasons when he also earned MVP honors...Was XC Most Improved as a sophomore...Personal best of 4:46 in the mile and 17:03 in the 5000m...Was co-president of Nottingham National Honor Society.");
        addRunner("Dan Nolte", 4, "Woodbury, CT", "10k", true, "Graduated from Nonnewaug HS where he was a member of the cross country and track and field teams...Named All-League in cross country for three years and was All-State as a senior...Helped Chiefs to league cross country title in his freshman and junior season and state runner up finish in his sophomore and senior years...Member of the National Honor Society.");
        addRunner("Leo Orsini", 1, "Mendon, NY", "800", false, "Participated on cross country, skiing and track and field teams at Honeoye Falls-Lima HS.");
        addRunner("Anthony Pane", 2, "Medina, NY", "Steeplechase", false, "Lettered in soccer, track and field and swimming for the Mustangs of Medina HS...Was elected co-captain of soccer team for two years and track and field team as a senior...Earned 1st Team All-League in soccer as a senior and was an All-East Scholar Athlete...Also, earned 1st place at the league outdoor track championships on 4x400m and 4x800m relays, placing 2nd individually in the 800m...Personal best of 2:03.63 in 800m...Soccer team was sectional champions in sophomore and senior seasons.");
        addRunner("Hunter Phinney", 2, "Cattaraugus, NY", "Steeplechase/10k", false, "Attended Cattaraugus-Little Valley Central School where he earned four letters in track and field and soccer, one letter in cross-country for the Timberwolves...2-time track team captain, 1-time for soccer and cross country...Earned track and field MVP honors as a junior and senior, XC also as a senior...Was All-League honoree in cross country and track and field...Class Salutatorian and was Treasurer of Student Council.");
        addRunner("John Pinto", 1, "Basking Ridge, NJ", "5k", false, "Ridge HS graduate...Lettered in cross country, indoor track and field and outdoor track and field for the Red Devils...Team was 2015 indoor Section II champions...Personal best time of 16:56 in the 5k...Was section leader of A Cappella choir and co-President of the Concert Choir.");
        addRunner("Matt Prohaska", 4, "Lockport, NY", "5k/10k", false, "Attended Starpoint HS where he lettered in cross country, track and field and soccer for the Spartans...Named XC and track captain as a junior and senior...Earned team MVP as a junior and senior in cross country and as a senior on the track as a middle distance runner...1st Team All-League in cross country and was a NY state meet qualifier as a junior and senior...Personal bests include 2:04 in the 800m and 16:35 in cross country (5k).");
        addRunner("Ryley Robinson", 1, "Clinton Corners, NY", "5k", false, "Attended Franklin Delano Roosevelt HS where he participated in cross country, indoor track and field and outdoor track and field...Captained the Presidents cross country team as a junior and senior, also leading both track teams in 2015-16...Team was MHAL cross country champions in 2015 and outdoor track and field champions in 2016...Personal best time of 17:06 in the 5k and 2:10 in the 800 meters...Named an MHAL Scholar Athlete in 2016.");
        addRunner("Kyle Ruffner", 3, "Lancaster, NY", "800/1500", false, "Graduated from Lancaster HS where he lettered in cross country, track and field and ice hockey for the Redskins...Was on 4x400m relay Section VI champion in indoor track and 4x800m relay champions in outdoor track...Recipient of Bausch and Lomb Honorary Science Award along with Science Student of the Year Award.");
    }

    public static RunnerManager get() {
        if(mRunnerManager == null) {
            mRunnerManager = new RunnerManager();
        }
        return mRunnerManager;
    }

    private void addRunner(String name, int year, String hometown, String event, boolean captain, String description){
        Runner runner = new Runner(name, year, hometown, event, captain, description);
        mList.add(runner);
    }

    public ArrayList<Runner> getList() {
        return mList;
    }
}
