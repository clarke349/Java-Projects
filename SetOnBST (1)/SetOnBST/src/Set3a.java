import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author Malik Clarke
 * @author Drew Jackson
 * @author Ethan Hunter
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        boolean found = false;

        // left and right trees to hold disassembled trees
        BinaryTree<T> left = new BinaryTree1<T>();
        BinaryTree<T> right = new BinaryTree1<T>();

        /*
         * Tries to find x in the tree. If x is less than the root, then the
         * left of the tree is searched. If x is greater than the root, then the
         * right tree is searched.
         */
        if (t.size() > 0 && !found) {
            T root = t.disassemble(left, right);

            if (root.compareTo(x) == 0) {
                found = true;
            } else if (root.compareTo(x) > 0) {

                found = isInTree(left, x);

            } else if (root.compareTo(x) < 0) {

                found = isInTree(right, x);

            }

            t.assemble(root, left, right);
        }

        return found;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        /*
         * left and right trees for the tree to be disassembled and re-assembled
         */
        BinaryTree<T> left = new BinaryTree1<T>();
        BinaryTree<T> right = new BinaryTree1<T>();

        // checks to see if a root is an empty tree where x can be added
        if (t.size() > 0) {
            T root = t.disassemble(left, right);
            if (root != null) {

                /*
                 * if x is greater than the root, it is added into the right
                 * tree, and if not, then it is added into the left tree.
                 */
                if (x.compareTo(root) < 0) {

                    insertInTree(left, x);

                } else if (x.compareTo(root) > 0) {

                    insertInTree(right, x);

                }

            } else {
                t.assemble(x, new BinaryTree1<T>(), new BinaryTree1<T>());
            }
            t.assemble(root, left, right);
        } else {
            t.assemble(x, new BinaryTree1<T>(), new BinaryTree1<T>());
        }

    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";

        // smallest label to be returned
        T smallest = t.root();

        // left and right trees to hold disassembled trees
        BinaryTree<T> left = new BinaryTree1<T>();
        BinaryTree<T> right = new BinaryTree1<T>();

        T root = t.disassemble(left, right);

        if (left.size() > 0) {
            smallest = removeSmallest(left);
            t.transferFrom(right);
            t.assemble(root, left, right);
        }

        return smallest;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        // will hold the removed label
        T removed = null;

        // left and right trees to hold disassembled subtrees
        BinaryTree<T> left = new BinaryTree1<T>();
        BinaryTree<T> right = new BinaryTree1<T>();

        // reports whether the label has been found and removed
        boolean found = false;

        if (t.size() > 0 && !found) {

            T root = t.disassemble(left, right);

            /*
             * if x is greater than the root, it is removed from the right tree,
             * and if not, then it is removed from the left tree.
             */
            if (x.compareTo(root) == 0) {

                removed = root;
                found = true;

            } else if (x.compareTo(root) < 0) {

                removeFromTree(left, x);

            } else if (x.compareTo(root) > 0) {

                removeFromTree(right, x);

            }

            /*
             * To avoid restructuring the entire tree, the following conditional
             * statement makes the smallest label found in the right subtree the
             * new root of the original tree t.
             */
            if (found) {
                T newRoot = removeSmallest(right);
                root = newRoot;
            }
            t.assemble(root, left, right);

        }

        return removed;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        this.tree = new BinaryTree1<T>();

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {

        this.createNewRep();

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        // adds x to this.tree
        insertInTree(this.tree, x);

    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        // removes x from this.tree
        T removed = removeFromTree(this.tree, x);

        return removed;
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        T removed = removeSmallest(this.tree);

        return removed;
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        boolean found = false;

        // reports whether or not x was found in this.tree
        found = isInTree(this.tree, x);

        return found;
    }

    @Override
    public final int size() {

        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
