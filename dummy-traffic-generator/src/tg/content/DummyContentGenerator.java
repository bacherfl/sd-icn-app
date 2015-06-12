package tg.content;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 12/06/15.
 */
public class DummyContentGenerator {

    public List<DummyContentInfo> generateDummyContent(int nrContentItems) {
        List<DummyContentInfo> dummyContentInfos = new ArrayList<>();
        
        for (int i = 0; i < nrContentItems; i++)
        {
            DummyContentInfo info = new DummyContentInfo("content" + i, Math.random() * 1000);
            dummyContentInfos.add(info);
        }
        return dummyContentInfos;
    }
}
