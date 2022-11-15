Vue.use(window.VueQuillEditor);
const fontSizeStyle = Quill.imports["attributors/style/size"];
fontSizeStyle.whitelist = ["12px", "14px", "16px", "18px", "20px", "24px", "28px", "32px", "36px"];
Quill.register(fontSizeStyle, true);
const Font = Quill.imports["attributors/style/font"];
// 字体的种类
const fonts = ["SimSun", "SimHei", "Microsoft-YaHei", "KaiTi", "FangSong"];
Font.whitelist = fonts;
Quill.register(Font, true);

const Parchment = Quill.imports["parchment"];
let config = {
    scope: Parchment.Scope.BLOCK,
    whitelist: ['right', 'center', 'justify']
};
let AlignStyle = new Parchment.Attributor.Style('align', 'text-align', config);
var Align = Quill.import('attributors/style/align');
Align.whitelist = ['right', 'center', 'justify'];
Quill.register(Align, true);

Quill.register('modules/eeSourceBtn', window.eeSourceBtn);
Vue.component('ll-editor', {
    template: '\
        <div>\
            <quill-editor :style="style_obj"\
                v-model="content"\
                :options="editorOption"\
                @blur="onEditorBlur($event)"\
                @focus="onEditorFocus($event)"\
                @change="onEditorChange($event)"\
                @ready="onEditorReady($event)"\
            />\
            <input type="hidden" :name="name" :value="content" />\
        </div>\
    ',
    props: ['html', 'name', 'width', 'maxheight'],
    created() {
        this.content = this.html;
        window.EQuill = this;
    },
    data() {
        // 富文本配置数组
        const toolbarOptions = [
            // ['emoji'],//表情包
            ["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 删除线
            ["blockquote", "code-block"], // 引用  代码块
            [{ header: 1 }, { header: 2 }], // 1、2 级标题
            [{ list: "ordered" }, { list: "bullet" }], // 有序、无序列表
            [{ script: "sub" }, { script: "super" }], // 上标/下标
            [{ indent: "-1" }, { indent: "+1" }], // 缩进
            [{ direction: "rtl" }], // 文本方向
            [{ size: fontSizeStyle.whitelist }],
            [{ font: fonts }],
            [{ header: [1, 2, 3, 4, 5, 6] }], // 标题
            [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
            [{ align: [] }], // 对齐方式
            ["clean"], // 清除文本格式
            ["image"],  //图片上传   
            // ["image", "video"], // 链接、图片、视频
        ];
        return {

            content: '',
            style_obj: {
                width: this.width,
                maxHeight: this.maxheight
            },
            editorOption: {
                modules: {
                    // 增加表情包
                    'emoji-toolbar': true,
                    'emoji-shortname': true,
                    imageResize: {
                        displayStyles: {
                            backgroundColor: 'black',
                            border: 'none',
                            color: 'white'
                        },
                        modules: ['Resize', 'DisplaySize', 'Toolbar']
                    },
                    eeSourceBtn: eeSourceBtn,
                    // 富文本编辑器配置
                    toolbar: {
                        container: toolbarOptions,
                        handlers: {
                            // 重写点击组件上的图片按钮要执行的代码
                            'image': function (value) {
                                document.querySelector('.quill-upload .el-icon-upload').click()
                            }
                        }
                    },
                },
                placeholder: "",
            },
        }
    },
    methods: {
        //失去焦点事件
        onEditorBlur(quill) {
            // console.log("editor blur!", quill);
        },
        // 获得焦点事件
        onEditorFocus(quill) {
            // console.log("editor focus!", quill);
        },
        // 准备富文本编辑器
        onEditorReady(quill,val) {
            this.uploadimages = val
            // console.log("editor ready!", quill);
        },
        // 内容改变事件
        onEditorChange({ quill, html, text }) {
            this.content = html;
            this.$emit("getcontent", html);
            this.quill = quill;
        },

        // 图片上传的方法

        // 文件上传之前校验的方法
        beforeAvatarUpload(response, file) {
            this.$emit('beforeAvatarUpload', file)
        },
        //图片上传成功的方法
        success(res, file, fileList) {
            // res为图片服务器返回的数据
            // 获取富文本组件实例
            let vm = this
            let quill = this.$refs.myQuillEditor.quill
            // 如果上传成功
            if (String(res.code) === '1') {
                // 获取光标所在位置
                const pos = quill.selection.savedRange.index //这个得注意下，网上很多都是不对的
                // 插入图片到光标位置
                quill.insertEmbed(pos, 'image', '/common/download?name=' + res.data)
                // 调整光标到最后
                quill.setSelection(length + 1)
                //将数据添加到数组
                vm.uploadimages.push(res.data.replace(/\\/g,"/"))
            } else {
                vm.$Message.error('图片插入失败')
            }
        },
    },
    //通过js初始化页面数据的方法
    watch: {
        // 为了让quill对象被页面捕获，这里需要做这个动作
        quill(val) {
            this.quill = val
        },
        html(val) {
            this.content = val;
        },
        serviceUrl(val) {
            this.serviceUrl = val
        },
        // getHeader(val) {
        //     this.getHeader = val
        // },
        editorData(val) {
            this.$emit('getEditorData', this.editorData)
        },
        content(val) {
            this.content = val
        },
        //初始化富文本编辑器上传的图片src地址
        pubImages(val){
            this.pubImages = val
        }
    },
})