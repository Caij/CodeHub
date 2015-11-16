package com.caij.codehub.present;

import com.caij.codehub.bean.FileTreeItem;
import com.caij.codehub.bean.Tree;
import com.caij.codehub.interactor.FileTreeInteractor;
import com.caij.codehub.interactor.InteractorFactory;
import com.caij.codehub.present.ui.ListUi;

/**
 * Author Caij
 * Email worldcaij@gmail.com
 * Created by Caij on 2015/11/16.
 */
public class FileTreePresent extends ListPresent<ListUi<FileTreeItem>, FileTreeItem>{

    private FileTreeInteractor mFileTreeInteractor;

    public FileTreePresent(ListUi<FileTreeItem> ui) {
        super(ui);
        mFileTreeInteractor = InteractorFactory.newPresentInstance(FileTreeInteractor.class);
    }

    public void loadFileTree(String name, String repo, String ref) {
        mFileTreeInteractor.loadFileTree(name, repo, ref, this, new DefaultInteractorCallback<Tree>(mUi) {
            @Override
            public void onError(int msgId) {
                defaultDealWithError(msgId, LoadType.FIRST);
            }

            @Override
            public void onSuccess(Tree tree) {
                defaultDealWithSuccess(tree.getTree(), LoadType.FIRST);
            }

            @Override
            public void onLoading() {
                defaultDealWithLoading(LoadType.FIRST);
            }
        });
    }

}
