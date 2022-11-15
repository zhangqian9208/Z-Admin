Vue.use(window.VueQuillEditor);
const fontSizeStyle = Quill.imports["attributors/style/size"];
fontSizeStyle.whitelist = [ "12px", "14px", "16px", "18px", "20px", "24px", "28px", "32px", "36px"];
Quill.register(fontSizeStyle, true);
const Font = Quill.imports["attributors/style/font"];
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

Quill.register( 'modules/eeSourceBtn', window.eeSourceBtn );
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
        return {
            content: '',
            style_obj: {
                width: this.width,
                maxHeight: this.maxheight
            },
            editorOption: {
                modules: {
                    imageResize:{
                        displayStyles:{
                            backgroundColor:'black',
                            border:'none',
                            color:'white'
                        },
                        modules:['Resize','DisplaySize','Toolbar']
                    },
                    eeSourceBtn: eeSourceBtn,
                    toolbar: [
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
                    ],
                },
                placeholder: "",
            },
        }
    },
    watch: {
        html(val) {
            this.content = val;
        },
    },
    methods: {
        onEditorBlur(quill) {
            // console.log("editor blur!", quill);
        },
        // 获得焦点事件
        onEditorFocus(quill) {
            // console.log("editor focus!", quill);
        },
        // 准备富文本编辑器
        onEditorReady(quill) {
            // console.log("editor ready!", quill);
        },
        // 内容改变事件
        onEditorChange({ quill, html, text }) {
            this.content = html;
            this.$emit("getcontent", html);
        },
    },
})