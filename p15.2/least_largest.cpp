
int smallest_larger_val(Tree t, int v) {
    if (!t) {
        throw ParameterException("Tree must be non-null.\n");
    } else {
        res = n_h(t, v, MAX_INT);
        if (res == MAX_INT) {
            throw AbsentException("No value larger than " + v +
                                " exists exists in tree.\n");
        } else {
            return res;
        }
    }
}

int slv_h(Tree t, int v, int m) {
    if (t.val > v) {
        if (t.left) {
            return slv_h(t.left, v, min(m, t.val)); 
        } else {
            return min(m, t.val);
        }
    } else {
        if (t.right) {
            return slv_h(t.right, v, m);
        } else {
            return m;
        }
    }
}

