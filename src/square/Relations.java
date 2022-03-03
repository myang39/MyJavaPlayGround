package square;

import java.util.*;
import java.util.stream.Collectors;

public class Relations {
//    public class FamilyTreeTest {
//        @Test
//        public void basicCaseTest() {
//            List<FamilyTree.FamilyInput> data = new ArrayList<>();
//            data.add(new FamilyTree.FamilyInput("A", "B", "C", "D"));
//            data.add(new FamilyTree.FamilyInput("C", "D", "E"));
//            FamilyTree tree = new FamilyTree(data);
//
//            Collection<String> got = tree.getParents("C");
//            assert(got, containsInAnyOrder("A", "B"));
//
//            got = tree.getAllChildren("A");
//            assertThat(got, containsInAnyOrder("C", "D"));
//
//            got = tree.getSiblings("C");
//            assertThat(got, containsInAnyOrder("D"));
//
//            got = tree.getGrandparents("E");
//            assertThat(got, containsInAnyOrder("A", "B"));
//        }
//
//        @Test
//        public void multipleRelationsTest() {
//            List<FamilyTree.FamilyInput> data = new ArrayList<>();
//            data.add(new FamilyTree.FamilyInput("A", "B", "C"));
//            data.add(new FamilyTree.FamilyInput("A", "D", "E"));
//            FamilyTree tree = new FamilyTree(data);
//
//            Collection<String> got = tree.getAllChildren("A");
//            assertThat(got, containsInAnyOrder("C", "E"));
//
//            got = tree.getAllChildren("B");
//            assertThat(got, containsInAnyOrder("C"));
//
//            got = tree.getParents("C");
//            assertThat(got, containsInAnyOrder("A", "B"));
//
//            got = tree.getParents("E");
//            assertThat(got, containsInAnyOrder("A", "D"));
//        }
//    }

    static public class FamilyTree {
        private Map<String, Person> people = new HashMap<>(); //Map<name, Person>

        class FamilyInput {
            List<String> parents;
            List<String> children;

            public FamilyInput(String parent1, String parent2, String... children) {
                this.parents = Arrays.asList(parent1, parent2);
                this.children = Arrays.asList(children);
            }
        }

        public FamilyTree(List<FamilyInput> data) {
            for (FamilyInput fi : data) {
                Person parent1 = this.people.get(fi.parents.get(0));
                if (parent1 == null) {
                    parent1 = new Person(fi.parents.get(0));
                    this.addPerson(parent1);
                }
                Person parent2 = this.people.get(fi.parents.get(1));
                if (parent2 == null) {
                    parent2 = new Person(fi.parents.get(1));
                    this.addPerson(parent2);
                }
                List<Person> parents = Arrays.asList(parent1, parent2);

                List<Person> children = new ArrayList<>(fi.children.size());
                for (String childName : fi.children) {
                    Person child;
                    if (people.containsKey(childName)) {
                        child = people.get(childName);
                    } else {
                        child = new Person(childName, parents);
                    }
                    this.addPerson(child);
                    children.add(child);
                }

                parent1.setChildren(parent2, children);
                parent2.setChildren(parent1, children);
            }
        }

        private void addPerson(Person person) {
            this.people.put(person.name, person);
        }

        private Person getPerson(String name) throws NoSuchPersonException {
            Person person = this.people.get(name);
            if (person == null) throw new NoSuchPersonException();
            return person;
        }

        public Collection<String> getParents(String name) {
            return this.getPerson(name).getParents()
                    .stream().map(Person::getName).collect(Collectors.toSet());
        }

        public Collection<String> getSiblings(String name) {
            Person person = this.getPerson(name);
            List<Person> parents = person.getParents(); //2 person list
            Person parent1 = parents.get(0);

            List<Person> sibl = parent1.getChildren(parents.get(1));
//            sibl.remove(person);

            //for loop
            return sibl.stream().filter(a -> !Objects.equals(a.name, name)).map(Person::getName).collect(Collectors.toSet());
        }

        public Collection<String> getAllChildren(String name) {
            return this.getPerson(name).getAllChildren()
                    .stream().map(Person::getName).collect(Collectors.toSet());
        }

        public Collection<String> getGrandparents(String name) {
            Person person = this.getPerson(name);
            ArrayList<Person> grandParents = new ArrayList<>(4);

            for (Person parent : person.getParents()) {
                grandParents.addAll(parent.getParents());
            }
            return grandParents
                    .stream().map(Person::getName).collect(Collectors.toSet());
        }

        class NoSuchPersonException extends RuntimeException {}

        static Integer globalId = 0;
        class Person {
            private Integer id;
            private String name;
            private List<Person> parents = new ArrayList<>();
            private Map<Person, List<Person>> relations = new HashMap<>(); //Map<partner, Collection<children>>

            public Person(String name) {
                this.name = name;
                id = globalId++;
            }

            @Override
            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Person)) {
                    return false;
                }
                Person p = (Person)o;
                return id.equals(p.id);
            }

            @Override
            public int hashCode() {
                return id;
            }

            public Person(String name, List<Person> parents) {
                this.name = name;
                this.parents = parents;
            }

            public String getName() {
                return name;
            }

            public List<Person> getParents() {
                return parents;
            }

            public List<Person> getChildren(Person partner) {
                return this.relations.get(partner);
            }

            public void setChildren(Person partner, List<Person> children) {
                this.relations.put(partner, children);
            }

            public List<Person> getAllChildren() {
                return this.relations.values().stream().flatMap(List::stream).collect(Collectors.toList());
            }
        }
    }
}
