/*
 * Copyright 2014 Netflix, Inc.
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
package nebula.plugin.bintray

import nebula.test.PluginProjectSpec

class NebulaBintraySyncPublishingPluginSpec extends PluginProjectSpec {
    @Override
    String getPluginName() {
        'nebula-bintray-sync-publishing'
    }

    def 'apply plugin'() {
        when:
        project.plugins.apply(NebulaBintraySyncPublishingPlugin)

        then:
        def uploadTask = project.tasks.getByName('bintrayUpload')
        uploadTask != null
        uploadTask.actions.size() == 2
    }

    def 'apply plugin with credentials'() {
        when:
        project.setProperty('sonatypeUsername', 'username')
        project.setProperty('sonatypePassword', 'password')
        project.plugins.apply(NebulaBintraySyncPublishingPlugin)

        then:
        def uploadTask = project.tasks.getByName('bintrayUpload')
        uploadTask != null
        uploadTask.actions.size() == 3
    }
}
