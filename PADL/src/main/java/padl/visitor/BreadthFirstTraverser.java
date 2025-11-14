package padl.visitor;

import java.util.ArrayDeque;
import java.util.Queue;

import padl.kernel.IConstituent;

public class BreadthFirstTraverser extends Traverser implements ITraverser {

    public BreadthFirstTraverser(IChildrenProvider pProvider) {
        super(pProvider);
    }

    @Override
    public void walk(final IConstituent p_root, final IVisitor p_visitor) {
        if (p_root == null || p_visitor == null) {
            return;
        }

        resetStatistics();
        final Queue<IConstituent> queue = new ArrayDeque<>();
        queue.add(p_root);

        while (!queue.isEmpty()) {
            final IConstituent node = queue.remove();
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
                updateNodesPruned(1);
                continue;
            }

            for (IConstituent child : getChildrenProvider().childrenOf(node)) {
                queue.add(child);
            }

            if (p_visitor instanceof IControllableVisitor) {
                ((IControllableVisitor) p_visitor).afterChildren(node);
            }
        }
    }



}
