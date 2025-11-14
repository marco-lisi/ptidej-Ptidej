package padl.visitor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import padl.kernel.IConstituent;

public class DepthFirstTraverser extends Traverser implements ITraverser {

    public DepthFirstTraverser(final IChildrenProvider provider) {
        super(provider);
    }

    @Override
    public void walk(final IConstituent p_root, final IVisitor p_visitor) {
        if (p_root == null || p_visitor == null) {
            return;
        }

        resetStatistics();
        final Deque<IConstituent> stack = new ArrayDeque<>();
        stack.push(p_root);

        while (!stack.isEmpty()) {
            final IConstituent node = stack.pop();
            updateNodesVisited(1);

            node.accept(p_visitor);

            VisitDecision decision = VisitDecision.CONTINUE;
            if (p_visitor instanceof IControllableVisitor) {
                decision = ((IControllableVisitor) p_visitor).beforeChildren(node);
            }

            if (decision == VisitDecision.STOP) {
                return;
            }

            if (decision == VisitDecision.SKIP_CHILDREN) {
                // TODO: should this necessarily be 1?
                updateNodesPruned(1);
                continue;
            }

            final List<IConstituent> children = new ArrayList<>();
            for (IConstituent child : getChildrenProvider().childrenOf(node)) {
                children.add(child);
            }

            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }

            if (p_visitor instanceof IControllableVisitor) {
                ((IControllableVisitor) p_visitor).afterChildren(node);
            }
        }
    }

}

