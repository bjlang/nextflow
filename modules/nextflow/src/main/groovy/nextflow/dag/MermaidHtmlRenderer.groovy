/*
 * Copyright 2013-2024, Seqera Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nextflow.dag

import java.nio.file.Path

/**
 * Render the DAG as a Mermaid diagram embedded in an HTML document.
 * See https://mermaid.js.org/ for more info.
 *
 * @author Ben Sherman <bentshermann@gmail.com>
 */
class MermaidHtmlRenderer implements DagRenderer {

    @Override
    void renderDocument(DAG dag, Path file) {
        final template = readTemplate()
        final network = new MermaidRenderer().renderNetwork(dag)
        file.text = template.replace('REPLACE_WITH_NETWORK_DATA', network)
    }

    private String readTemplate() {
        final writer = new StringWriter()
        final res = MermaidHtmlRenderer.class.getResourceAsStream('mermaid.dag.template.html')
        int ch
        while( (ch=res.read()) != -1 ) {
            writer.append(ch as char)
        }
        writer.toString()
    }
}
