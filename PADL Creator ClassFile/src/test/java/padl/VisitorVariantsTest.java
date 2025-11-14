package padl;

import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IConstituent;
import padl.kernel.impl.Factory;
import padl.visitor.*;

public final class VisitorVariantsTest {

    public static void main(String[] args) {
        try {
            final ICodeLevelModel model =
                    Factory.getInstance().createCodeLevelModel("TestModel");

            model.create(new CompleteClassFileCreator(
                    new String[] { "path/to/classes/" }
            ));

            final IChildrenProvider provider = new DefaultChildrenProvider();

            final SkipTestPackagesWalker walker1 = new SkipTestPackagesWalker();
            final DepthFirstTraverser dfs = new DepthFirstTraverser(provider);
            dfs.walk((IConstituent) model.getIteratorOnTopLevelEntities().next(), walker1);
            walker1.printStatistics();

            final SkipTestPackagesWalker walker2 = new SkipTestPackagesWalker();
            final BreadthFirstTraverser bfs = new BreadthFirstTraverser(provider);
            bfs.walk((IConstituent) model.getIteratorOnTopLevelEntities().next(), walker2);
            walker2.printStatistics();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


