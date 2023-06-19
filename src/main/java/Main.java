import java.sql.SQLOutput;
import java.util.*;

public class Main {
    static class User implements Comparable<User> {
        private Integer id;
        private String name;
        private String sex;
        private Integer age;

        public User() {
        }

        public User(Integer id, String name, String sex, Integer age) {
            this.id = id;
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name=" + name +
                    ", sex=" + sex +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(User o) {
            return this.getName().compareTo(o.getName());
        }
    }

    private static String generateName(String[] character) {
        List word = Arrays.asList(character);
        StringBuffer name = new StringBuffer();
        Collections.shuffle(word);
        for (int i = 0; i < 3; i++) {
            name.append(word.get(i));
        }
        return name.toString();
    }

    public static void main(String[] args) {
        String[] character = {"f", "r", "u", "i", "t"};
        String[] sex = {"Male", "Female"};
        Random r = new Random();
        int min = 20;
        int max = 23;
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            int age = r.nextInt(max - min) + min;
            int rIndexSex = r.nextInt(sex.length);
            User user = new User();
            user.setId(i);
            user.setAge(age);
            user.setSex(sex[rIndexSex]);
            user.setName(generateName(character));
            users.add(user);
        }
        printUser(users);
        //EX6
        System.out.println("\nSort User By Name --> Start !");
        Collections.sort(users);
        printUser(users);
        //EX7
        System.out.println("\nCount Sex --> Start !");
        countSex(users);
        //EX8
        System.out.println("\nCollection Same Name ---> Start !");
        collectionSameName(users);
        //EX9
        System.out.println("\nCollection Same Name & Age ---> Start !");
        collectUserHasSameNameAndAge(users);
    }

    //EX7
    private static void countSex(List<User> users) {
        final String Male = "male";
        int total = 0;
        for (int i = 0; i < users.size(); i++) {
            if (Male.equalsIgnoreCase(users.get(i).getSex())) {
                total += 1;
            }
        }
        System.out.println("Total : " + users.size() + " Male : " + total + " ---- Female : " + (users.size() - total));
    }

    //EX8
    private static Map<String, List<User>> collectionSameName(List<User> users) {
        Map<String, List<User>> clName = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            List<User> nameUsers = clName.get(user.getName());
            if (nameUsers == null) {
                nameUsers = new ArrayList<>();
                clName.put(user.getName(), nameUsers);
            }
            nameUsers.add(user);
        }
        System.out.println(clName);
        return clName;
    }

    //EX9
    private static Map<String, Map<Integer, List<User>>> collectUserHasSameNameAndAge(List<User> users) {
        Map<String, Map<Integer, List<User>>> map = new HashMap<>();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            Map<Integer, List<User>> nameUsers = map.get(user.getName());
            if (nameUsers == null) {
                nameUsers = new HashMap<>();
                map.put(user.getName(), nameUsers);
            }

            List<User> ages = nameUsers.get(user.getAge());
            if (ages == null) {
                ages = new ArrayList<>();
                nameUsers.put(user.getAge(), ages);
            }
            ages.add(user);
        }
        System.out.println(map);

        return map;
    }

    private static void printUser(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
    }
}